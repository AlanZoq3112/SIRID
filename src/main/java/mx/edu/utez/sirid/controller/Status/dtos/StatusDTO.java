package mx.edu.utez.sirid.controller.Status.dtos;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.sirid.enums.EStatus;
import mx.edu.utez.sirid.model.Status.Status;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StatusDTO {
    private long id;

    @NotEmpty(message = "Campo obligatorio")
    @NotNull
    @NotBlank
    @Length(min=1,max=120)
    private EStatus name;

    public Status getStatus(){
        return new Status(
                getId(),
                getName()
        );
    }

}
