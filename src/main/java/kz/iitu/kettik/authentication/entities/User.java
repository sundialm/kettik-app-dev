package kz.iitu.kettik.authentication.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kz.iitu.kettik.interfaces.AbstractEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User extends AbstractEntity {

    @Email
    @NotBlank
    @NotNull(message = "Введите корректный мэйл")
    @Pattern(regexp = "([a-zA-Z0-9]+(?:[._+-][a-zA-Z0-9]+)*)@([a-zA-Z0-9]+(?:[.-][a-zA-Z0-9]+)*[.][a-zA-Z]{2,})", message = "Введите корректный мэйл")
    private String email;

    @Column
    @NotNull
    @NotBlank
    @Size(min = 2, max = 70, message = "Имя пользователя должен содержать минимум 2 символа и максимум 30")
    @Pattern(regexp = "^[a-zA-Z]*$", message = "Имя пользователя должен содержать только буквы!")
    private String fullName;

    @Column
    @NotNull
    @NotBlank
    @Pattern(regexp = "[0-9]+", message = "Номер телефона должен содержать только цифры")
    private String phone;

    @JsonIgnore
    @NotNull(message = "Введите корректный пароль")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$", message = "Пароль должен содержать букву в верхнем регистре, нижнем регистре, цифру, длина - 8 символов")
    private String password;

    @OneToOne
    private Avatar avatar;

    @Column
    @JsonIgnore
    private Boolean enable = false;

    @Builder.Default
    @ManyToMany(fetch = FetchType.EAGER)
    @JsonIgnore
    private Collection<Role> roles = new ArrayList<Role>();
}
