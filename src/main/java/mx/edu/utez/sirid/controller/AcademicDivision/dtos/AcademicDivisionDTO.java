package mx.edu.utez.sirid.controller.AcademicDivision.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.sirid.enums.DivisionesAcademicas;
import mx.edu.utez.sirid.model.AcademicDivision.AcademicDivision;
import mx.edu.utez.sirid.model.User.User;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AcademicDivisionDTO {
    private long id;

    @NotEmpty(message = "Campo obligatorio")
    @Size(min = 1, max = 100)
    private DivisionesAcademicas name;

    private Set<User> user;

    public AcademicDivision getAcademicDivision() {
        return  new AcademicDivision(
                getId(),
                getName(),
                getUser()
        );
    }
}
