package mx.edu.utez.sirid.controller.Status.dtos;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.sirid.enums.EStatus;
import mx.edu.utez.sirid.model.Classroom.Classroom;
import mx.edu.utez.sirid.model.Incidence.Incidence;
import mx.edu.utez.sirid.model.Status.Status;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StatusDTO {
    private long id;

    @NotEmpty(message = "Campo Obligatorio")
    @Size(min = 1, max = 100)
    private EStatus name;

    private List<Incidence> incidence;

    public Status getStatus(){
        return new Status(
                getId(),
                getName(),
                getIncidence()
        );
    }

}
