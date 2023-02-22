package mx.edu.utez.sirid.model.AcademicDivision;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "academicDivision")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AcademicDivision {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false, length = 10)
    String name;
}
