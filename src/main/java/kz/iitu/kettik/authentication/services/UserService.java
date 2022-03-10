package kz.iitu.kettik.authentication.services;

import kz.iitu.kettik.authentication.entities.User;
import kz.iitu.kettik.authentication.repositories.UserRepository;
import kz.iitu.kettik.authentication.specifications.UserSpecifications;
import kz.iitu.kettik.exeptions.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class UserService{

    private final UserRepository repository;
    private final PasswordEncoder encoder;

    public User save(User user){
        return repository.save(user);
    }

    public User findByEmail(String email){
        return repository.findByEmail(email).orElseThrow(() -> {
            return new EntityNotFoundException("Пользователь", email);
        });
    }

    public boolean existByEmail(String email){
        return repository.existsByEmail(email);
    }

    public boolean isAuth(String email, String password){
//        password = encoder.encode(password);
        System.out.println("-".repeat(50));
        System.out.println("-".repeat(50));
        System.out.println("-".repeat(50));
        System.out.println("-".repeat(50));
        System.out.println(password);
        System.out.println("-".repeat(50));
        System.out.println("-".repeat(50));
        System.out.println("-".repeat(50));
        System.out.println("-".repeat(50));
        return repository
                .findOne(Specification
                        .where(UserSpecifications
                                .isAuth(email, password)))
                .isPresent();
    }

    public void enableById(Integer id){
        User user = repository.getById(id);
        user.setEnable(true);
        save(user);
    }
}
