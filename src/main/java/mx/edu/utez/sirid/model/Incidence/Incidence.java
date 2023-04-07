package mx.edu.utez.sirid.model.Incidence;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.sirid.model.Classroom.Classroom;
import mx.edu.utez.sirid.model.Status.Status;
import mx.edu.utez.sirid.model.User.User;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

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
    private Date created_at;

    @Column(nullable = false)
    private Date finish_at;

    @Column( nullable = false )
    private Date last_modify;

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

    @OneToMany(mappedBy = "incidence")
    private List<Resources> resourcesList;




}
