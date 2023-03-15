package mx.edu.utez.sirid.model.Classroom;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.sirid.model.Area.Area;

import mx.edu.utez.sirid.model.Incidence.Incidence;
import mx.edu.utez.sirid.model.Type.Type;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "classrooms")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Classroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false, length = 20)
    private String name;
    @Column(name = "status", nullable = false, columnDefinition = " tinyint default 1")
    private Boolean status;


    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false, referencedColumnName = "id")
    private Type type;

    @ManyToOne
    @JoinColumn(name = "area_id", nullable = false, referencedColumnName = "id")
    private Area area;

    @OneToMany(mappedBy = "classroom")
    @JsonIgnore
    private List<Incidence> incidence;


}