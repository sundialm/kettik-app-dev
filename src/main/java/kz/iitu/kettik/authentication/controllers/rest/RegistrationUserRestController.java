package kz.iitu.kettik.authentication.controllers.rest;

import kz.iitu.kettik.authentication.DTO.UserDTO;
import kz.iitu.kettik.authentication.entities.User;
import kz.iitu.kettik.authentication.entities.VerificationToken;
import kz.iitu.kettik.authentication.events.RegistrationCompleteEvent;
import kz.iitu.kettik.authentication.listeners.RegistrationListener;
import kz.iitu.kettik.authentication.mappers.UserMapper;
import kz.iitu.kettik.authentication.services.UserService;
import kz.iitu.kettik.exeptions.BadRequestException;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/api/user" )
public class RegistrationUserRestController {

    private final UserService service;
    private final UserMapper mapper;
    private final ApplicationEventPublisher publisher;
    private final RegistrationListener registrationListener;
    private final PasswordEncoder encoder;

    @PostMapping("/registration")
    public ResponseEntity<?> register(@Valid UserDTO userDTO, HttpServletRequest request, BindingResult bindingResult){

        if(service.existByEmail(userDTO.getEmail())){
            throw new BadRequestException("Email address already in use.");
        }

        if(bindingResult.hasFieldErrors()){
            return (ResponseEntity<?>) ResponseEntity.badRequest().contentType(MediaType.valueOf(String.valueOf(
                    bindingResult.getFieldErrors().get(0).getDefaultMessage())));
        }

        User registeredUser = service.save(mapper.toEntity(userDTO));
//        registeredUser.setPassword(encoder.encode(registeredUser.getPassword()));
//        service.save(registeredUser);
        publisher.publishEvent(new RegistrationCompleteEvent(registeredUser,  ("http://" + request.getServerName() + ":"
                + request.getServerPort() + request.getContextPath() + "/confirm"), request.getLocale()));
        return  ResponseEntity.ok(new VerificationToken(registeredUser, registrationListener.getToken()));
    }

}
