package mx.edu.utez.sirid.controller.Classroom.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.sirid.model.Area.Area;
import mx.edu.utez.sirid.model.Classroom.Classroom;
import mx.edu.utez.sirid.model.Type.Type;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ClassroomDTO {

    private Long id;

    @NotEmpty(message = "Campo obligatorio")
    @NotNull
    @NotBlank
    @Length(min=1,max=120)
    private String name;

    private Boolean status;

    private Type type;

    private Area area;

    public Classroom castToClassroom(){
        return new Classroom(
                getId(),
                getName(),
                getStatus(),
                getType(),
                getArea()
        );
    }

    public Classroom changeStatus(){
        return new Classroom(
                getId(),
                null,
                getStatus(),
                null,
                null
        );
    }
}
