package mx.edu.utez.sirid.controller.AcademicDivision.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.sirid.model.AcademicDivision.AcademicDivision;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AcademicDivisionDTO {
    private long id;

    @NotEmpty(message = "Campo obligatorio")
    @Size(min = 1, max = 100)
    private String name;

    public AcademicDivision getAcademicDivision() {
        return new AcademicDivision(
                getId(),
                getName()
        );
    }
}
