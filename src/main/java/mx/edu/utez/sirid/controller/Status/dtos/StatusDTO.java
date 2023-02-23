package mx.edu.utez.sirid.controller.Status.dtos;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.sirid.model.Status.Status;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StatusDTO {
    private long id;

    @NotEmpty(message = "Campo Obligatorio")
    @Size(min = 1, max = 100)
    private String name;

    public Status getStatus(){
        return new Status(
                getId(),
                getName()
        );
    }

}
