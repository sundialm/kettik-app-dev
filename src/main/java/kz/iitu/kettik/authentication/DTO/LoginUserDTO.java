package kz.iitu.kettik.authentication.DTO;

import kz.iitu.kettik.interfaces.AbstractDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginUserDTO extends AbstractDTO {

    @Email
    @NotNull
    @NotBlank
    private String email;

    @NotNull
    @NotBlank
    private String password;

}
