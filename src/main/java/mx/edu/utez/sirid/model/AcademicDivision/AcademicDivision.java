package mx.edu.utez.sirid.model.AcademicDivision;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @Column(unique = true, nullable = false, length = 50)
    private String name;

    public AcademicDivision(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "academicDivision")
    @JsonIgnore
    private Set<User> user;
}
