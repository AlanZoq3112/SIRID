package mx.edu.utez.sirid.model.AcademicDivision;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.sirid.enums.DivisionesAcademicas;
import mx.edu.utez.sirid.model.User.User;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "academicDivisions")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AcademicDivision {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false, length = 10)
    private DivisionesAcademicas name;

    public AcademicDivision(DivisionesAcademicas name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "academicDivision")
    @JsonIgnore
    private Set<User> user;
}
