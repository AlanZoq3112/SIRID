package mx.edu.utez.sirid.model.Classroom;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.sirid.model.Area.Area;
import mx.edu.utez.sirid.model.Type.Type;

import javax.persistence.*;

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


    @OneToOne
    @JoinColumn(name = "type_id", nullable = false, referencedColumnName = "id")
    private Type type;


    @OneToOne
    @JoinColumn(name = "area_id", nullable = false, referencedColumnName = "id")
    private Area area;

}
