package kz.iitu.kettik.authentication.listeners;

import kz.iitu.kettik.authentication.entities.User;
import kz.iitu.kettik.authentication.events.RegistrationCompleteEvent;
import kz.iitu.kettik.authentication.services.VerificationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class RegistrationListener implements ApplicationListener<RegistrationCompleteEvent> {

    private final VerificationTokenService service;

    @Qualifier("messageSource")
    private final MessageSource messages;
    @Qualifier("mailSender")
    private final JavaMailSender mailSender;
    private final String token = UUID.randomUUID().toString();

    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event){
        this.confirmRegistration(event);
    }

    private void confirmRegistration(RegistrationCompleteEvent event) {
        User user = event.getUser();
        service.createVerificationToken(user, this.token);
        String recipient = user.getEmail();
        String subject = "Registration Confirmation";
        String confirmationUrl = "https://kettik-tour-app-dev.herokuapp.com/confirm?token=" + this.token;

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipient);
        email.setSubject(subject);
        email.setText("Добро пожаловать в Kettik tour! " + "\r\n" +
                "Пожалуйста, подтвердите свой адрес электронной почты перейдя по данной ссылке: " + "\r\n" +
                confirmationUrl  + "\r\n" +
                "С уважением Kettik.tour.app!");

        mailSender.send(email);
    }

    public String getToken(){
        return this.token;
    }
}
