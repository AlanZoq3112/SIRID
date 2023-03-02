package mx.edu.utez.sirid.controller.Role.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.sirid.model.Role.Role;
import mx.edu.utez.sirid.model.User.User;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class RoleDTO {
    private Long id;
    @NotEmpty(message = "Campo obligatorio")
    @NotNull
    @NotBlank
    @Length(min=1,max=120)
    private String name;

    private User user;

    public Role getRole(){
        return new Role(
                getId(),
                getName(),
                getUser()
        );
    }
}
