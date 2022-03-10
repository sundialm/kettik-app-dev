package kz.iitu.kettik.authentication.entities;

import kz.iitu.kettik.interfaces.AbstractEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Calendar;
import java.util.Date;

@Entity
@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "verification_tokens")
public class VerificationToken extends AbstractEntity
{
    @Transient
    private static final int EXPIRATION = 60 * 24;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    @Column
    @NotNull
    @NotBlank
    private String token;

    @Column
    private Date expiryDate;

    public VerificationToken(User user, String token) {
        this.user = user;
        this.token = token;
        this.expiryDate = calculateExpiryDate();
    }

    private Date calculateExpiryDate() {
        Calendar calendar = Calendar.getInstance();
        long time = calendar
                            .getTime()
                            .getTime();
        return new Date(time);
    }
}
