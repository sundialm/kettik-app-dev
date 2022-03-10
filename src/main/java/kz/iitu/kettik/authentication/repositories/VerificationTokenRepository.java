package kz.iitu.kettik.authentication.repositories;

import kz.iitu.kettik.authentication.entities.User;
import kz.iitu.kettik.authentication.entities.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Integer>, JpaSpecificationExecutor<VerificationToken> {
    VerificationToken findByToken(String token);
    VerificationToken findByUser(User user);
}
