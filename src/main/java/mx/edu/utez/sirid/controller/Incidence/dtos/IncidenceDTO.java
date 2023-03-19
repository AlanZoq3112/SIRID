package mx.edu.utez.sirid.controller.Incidence.dtos;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.sirid.model.Classroom.Classroom;
import mx.edu.utez.sirid.model.Incidence.Incidence;
import mx.edu.utez.sirid.model.Status.Status;
import mx.edu.utez.sirid.model.User.User;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class IncidenceDTO {
    private Long id;
    @NotNull
    @NotBlank
    @Length(min = 1, max = 45)
    private String title;
    @Length(min = 1, max = 200)
    private String desription;
    private Long asigned_at;
    private Timestamp created_at;
    private Timestamp finish_at;
    private Timestamp last_modify;


    private Status status;
    private Classroom classroom;
    private User docente;
    private User personalSoporte;

    public Incidence castToIncidence() {
        return new Incidence(
                getId(),
                getTitle(),
                getDesription(),
                getCreated_at(),
                getFinish_at(),
                getLast_modify(),
                getClassroom(),
                getStatus(),
                getDocente(),
                getPersonalSoporte()
        );
    }
}
