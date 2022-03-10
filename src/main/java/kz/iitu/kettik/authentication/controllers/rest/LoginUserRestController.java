package kz.iitu.kettik.authentication.controllers.rest;

import kz.iitu.kettik.authentication.DTO.LoginUserDTO;
import kz.iitu.kettik.authentication.entities.User;
import kz.iitu.kettik.authentication.entities.VerificationToken;
import kz.iitu.kettik.authentication.listeners.RegistrationListener;
import kz.iitu.kettik.authentication.services.UserService;
import kz.iitu.kettik.exeptions.BadRequestException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/api/user" )
public class LoginUserRestController{

    private final UserService service;
    private final RegistrationListener registrationListener;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid LoginUserDTO userDTO) {

        boolean isAuth = service.isAuth(userDTO.getEmail(), userDTO.getPassword());

        if(!isAuth){
            throw new BadRequestException("Password or email is incorrect");
        }

        User user = service.findByEmail(userDTO.getEmail());
        return  ResponseEntity.ok(new VerificationToken(user, registrationListener.getToken()));
    }

}
