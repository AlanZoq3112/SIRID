package mx.edu.utez.sirid.security.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class LoginDTO {

    @NotEmpty
    private String email;

    @NotEmpty
    private String password;
}
