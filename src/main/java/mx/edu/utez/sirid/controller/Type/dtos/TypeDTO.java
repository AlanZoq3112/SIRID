package mx.edu.utez.sirid.controller.Type.dtos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.sirid.enums.TiposDeSalones;
import mx.edu.utez.sirid.model.Classroom.Classroom;
import mx.edu.utez.sirid.model.Type.Type;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class TypeDTO {
    private Long id;
    @NotNull
    @NotBlank
    @Length(min = 1, max = 50)
    private TiposDeSalones name;

    private Set<Classroom> classroom;

    public Type castToStatus(){
        return new Type(
                getId(),
                getName(),
                getClassroom()
        );
    }

}
