package kz.iitu.kettik.authentication.services;

import kz.iitu.kettik.authentication.entities.User;
import kz.iitu.kettik.authentication.entities.VerificationToken;
import kz.iitu.kettik.authentication.repositories.VerificationTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class VerificationTokenService {
    private final VerificationTokenRepository repository;
    private final UserService userService;

    public User getUser(String token){
        return repository
                        .findByToken(token)
                        .getUser();
    }

    public void createVerificationToken(User user, String token){
        repository.save(new VerificationToken(user, token));
    }

    public User confirm(String token){
        User user = repository.findByToken(token).getUser();
        userService.enableById(user.getId());
        return user;
    }

}
