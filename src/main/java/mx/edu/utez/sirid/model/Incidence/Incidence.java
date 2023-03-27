package mx.edu.utez.sirid.model.Incidence;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.sirid.model.Classroom.Classroom;
import mx.edu.utez.sirid.model.Status.Status;
import mx.edu.utez.sirid.model.User.User;
import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;

@Entity
@Table(name = "incidences")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Incidence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String title;

    @Column( nullable = false, columnDefinition = "text")
    private String description;

    @Column( nullable = false)
    private LocalDate created_at;

    @Column(nullable = false)
    private LocalDate finish_at;

    @Column( nullable = false )
    private LocalDate last_modify;

    @ManyToOne
    @JoinColumn(name = "id_classroom", nullable = false, referencedColumnName = "id")
    private Classroom classroom;

    @ManyToOne
    @JoinColumn(name = "id_status", nullable = false, referencedColumnName = "id")
    private Status status;

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false, referencedColumnName = "id")
    private User docente;

    @ManyToOne
    @JoinColumn(name = "asigned_at", referencedColumnName = "id")
    private User personalSoporte;

}
