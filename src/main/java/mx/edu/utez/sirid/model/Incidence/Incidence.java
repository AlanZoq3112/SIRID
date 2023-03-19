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

    @Column(unique = true,nullable = false, length = 45)
    private String title;

    @Column(unique = true, nullable = false, length = 200)
    private String description;

    @Column(unique = true, nullable = false)
    private Timestamp created_at;

    @Column(unique = true, nullable = false)
    private Timestamp finish_at;

    @Column(unique = true, nullable = false)
    private Timestamp last_modify;

    @ManyToOne
    @JoinColumn(name = "id_classroom", nullable = false, referencedColumnName = "id")
    private Classroom classroom;

    @ManyToOne
    @JoinColumn(name = "id_status", nullable = false, referencedColumnName = "id")
    private Status status;

    @ManyToOne
    @JoinColumn(name = "cerated_by", nullable = false, referencedColumnName = "id")
    private User docente;

    @ManyToOne
    @JoinColumn(name = "asigned_at", referencedColumnName = "id")
    private User personalSoporte;

}
