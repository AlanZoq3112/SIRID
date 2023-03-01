package mx.edu.utez.sirid.model.AcademicDivision;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.sirid.model.User.User;

import javax.persistence.*;

@Entity
@Table(name = "academicDivisions")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AcademicDivision {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, nullable = false, length = 10)
    String name;

    @OneToOne(mappedBy = "academicDivision")
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;
}
