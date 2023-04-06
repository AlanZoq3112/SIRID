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
import java.util.Date;


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
    private Date created_at;
    private Date finish_at;
    private Date last_modify;
    private Status status;
    private Classroom classroom;
    private User docente;
    private User personalSoporte;

    public Incidence castToIncidence() {
        System.out.println("fecha de creacion"+this.getCreated_at());
        System.out.println("Status de la incidencias "+status);
        status = new Status( (long)1,"Pendiente",null);
        return new Incidence(
                getId(),
                getTitle(),
                getDescription(),
                new Date(),
                getFinish_at(),
                new Date(),
                getClassroom(),
                status,
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
        status = new Status( (long)2,"Activa",null);
        return new Incidence(
                getId(),
                null,
                null,
                null,
                null,
                new Date(),
                null,
                status,
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
                new Date(),
                null,
                null,
                null,
                null
        );
    }

    public Incidence finalizeIncident() {
        status = new Status( (long)3,"Concluida",null);
        return new Incidence(
                getId(),
                getTitle(),
                getDescription(),
                getCreated_at(),
                new Date(),
                new Date(),
                getClassroom(),
                status,
                getDocente(),
                getPersonalSoporte()
        );
    }



}
