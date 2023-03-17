package mx.edu.utez.sirid.controller.User.dtos;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.sirid.model.AcademicDivision.AcademicDivision;
import mx.edu.utez.sirid.model.Role.Role;
import mx.edu.utez.sirid.model.User.User;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDTO {
    private Long id;
    @NotEmpty(message = "Campo Obligatorio")
    @Length(min = 1, max = 45)
    private String name;

    private String primer_apellido;

    private String segundo_apellido;

    private String correo_electronico;

    private String contrasena;

    private Boolean status;

    private Boolean changePassword;

    private AcademicDivision academicDivision;

    private Role role;
    public User getUser(){
        return new User(
                getId(),
                getName(),
                getPrimer_apellido(),
                getSegundo_apellido(),
                getCorreo_electronico(),
                getContrasena(),
                getStatus(),
                getChangePassword(),
                getRole(),
                getAcademicDivision()
        );
    }
}
