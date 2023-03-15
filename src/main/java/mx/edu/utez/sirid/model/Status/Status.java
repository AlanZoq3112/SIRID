package mx.edu.utez.sirid.model.Status;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.sirid.enums.EStatus;
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

    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false, length = 10)
    private EStatus name;

    @OneToMany(mappedBy = "status")
    @JsonIgnore
    private List<Incidence> incidence;

    public Status( EStatus name) {
        this.name = name;
    }
}
