package kz.iitu.kettik.authentication.events;

import kz.iitu.kettik.authentication.entities.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

import java.util.Locale;

@Getter @Setter
public class RegistrationCompleteEvent extends ApplicationEvent {

    private User user;
    private String applicationUrl;
    private Locale locale;

    public RegistrationCompleteEvent(User user, String applicationUrl, Locale locale) {
        super(user);
        this.user = user;
        this.applicationUrl = applicationUrl;
        this.locale = locale;
    }
}
