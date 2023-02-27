package mx.edu.utez.sirid.model.Area;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.sirid.model.Classroom.Classroom;
import javax.persistence.*;

@Entity
@Table(name = "area")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Area {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false, length = 20)
    private String name;

    @OneToOne(mappedBy = "area")
    @JsonIgnore
    private Classroom classroom;
}
