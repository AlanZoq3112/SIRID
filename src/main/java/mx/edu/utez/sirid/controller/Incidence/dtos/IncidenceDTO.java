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
import java.time.LocalDate;


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
    @Length(min = 1)
    private String description;
    private Long asigned_at;
    private LocalDate created_at=LocalDate.now();
    private LocalDate finish_at;
    private LocalDate last_modify=LocalDate.now();
    private Status status;
    private Classroom classroom;
    private User docente;
    private User personalSoporte;

    public Incidence castToIncidence() {
        System.out.println("fecha de creacion"+this.getCreated_at());
        return new Incidence(
                getId(),
                getTitle(),
                getDescription(),
                LocalDate.now(),
                getFinish_at(),
                LocalDate.now(),
                getClassroom(),
                getStatus(),
                getDocente(),
                getPersonalSoporte()
        );
    }

    public Incidence changeStatus() {
        return new Incidence(
                getId(),
                null,
                null,
                null,
                null,
                getLast_modify(),
                null,
                getStatus(),
                null,
                null
        );
    }

    public Incidence changePersonalSuport() {
        return new Incidence(
                getId(),
                null,
                null,
                null,
                null,
                getLast_modify(),
                null,
                null,
                null,
                getPersonalSoporte()
        );
    }

    public Incidence changeLastModify() {
        return new Incidence(
                getId(),
                null,
                null,
                null,
                null,
                getLast_modify(),
                null,
                null,
                null,
                null
        );
    }

}
