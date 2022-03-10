package kz.iitu.kettik.authentication.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kz.iitu.kettik.interfaces.AbstractDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO extends AbstractDTO {

    @Email
    @NotNull(message = "Введите корректный мэйл")
    @Pattern(regexp = "([a-zA-Z0-9]+(?:[._+-][a-zA-Z0-9]+)*)@([a-zA-Z0-9]+(?:[.-][a-zA-Z0-9]+)*[.][a-zA-Z]{2,})", message = "Введите корректный мэйл")
    private String email;

    @Column(nullable = false)
    @Size(min = 2, max = 70, message = "Имя пользователя должен содержать минимум 2 символа и максимум 30")
    @Pattern(regexp = "^[a-zA-Z]*$", message = "Имя пользователя должен содержать только буквы!")
    private String fullName;

    @Column(nullable = false)
    @Pattern(regexp = "[0-9]+", message = "Номер телефона должен содержать только цифры")
    private String phone;

    @JsonIgnore
    @NotNull(message = "Введите корректный пароль")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$", message = "Пароль должен содержать букву в верхнем регистре, нижнем регистре, цифру, длина - 8 символов")
    private String password;

    @JsonIgnore
    private String avatar;
}
