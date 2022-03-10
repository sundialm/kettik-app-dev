package kz.iitu.kettik.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfiguration {
    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
//elysiumelectricityshop@gmail.com
//G-hwzs&%G7>)E>YD
        mailSender.setUsername("kettik.tour.app@gmail.com");
        mailSender.setPassword("aktos2020");

        Properties properties = mailSender.getJavaMailProperties();

        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.auth", "trues");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        return mailSender;
    }
}
