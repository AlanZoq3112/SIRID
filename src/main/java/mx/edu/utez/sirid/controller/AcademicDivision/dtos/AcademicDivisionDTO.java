package mx.edu.utez.sirid.controller.AcademicDivision.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.sirid.enums.DivisionesAcademicas;
import mx.edu.utez.sirid.model.AcademicDivision.AcademicDivision;
import mx.edu.utez.sirid.model.User.User;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AcademicDivisionDTO {
    private long id;

    @NotEmpty(message = "Campo obligatorio")
    @NotNull
    @NotBlank
    @Length(min=1,max=120)
    private DivisionesAcademicas name;

    private Set<User> user;


    public AcademicDivision getAcademicDivision() {
        return new AcademicDivision(
                getId(),
                getName(),
                getUser()
        );
    }
}
