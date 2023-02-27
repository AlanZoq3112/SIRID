package mx.edu.utez.sirid.controller.Area.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.sirid.model.Area.Area;
import mx.edu.utez.sirid.model.Classroom.Classroom;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AreaDTO {
    private Long id;
    @NotNull
    @NotBlank
    @Length(min = 1, max = 50)
    private String name;

    private Classroom classroom;
    public Area getArea() {
        return new Area(
                getId(),
                getName(),
                getClassroom()
        );
    }
}
