package mx.edu.utez.sirid.model.Status;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.sirid.model.Incidence.Incidence;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "status")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 10)
    private String name;

    @OneToMany(mappedBy = "status")
    @JsonIgnore
    private List<Incidence> incidenceList;

    public Status( String name) {
        this.name = name;
    }
}
