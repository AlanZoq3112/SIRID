package mx.edu.utez.sirid.model.Type;

<<<<<<< HEAD
import com.fasterxml.jackson.annotation.JsonIgnore;
=======
>>>>>>> bda133956100d56c6e15099595aa72cdf3599592
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
<<<<<<< HEAD
import mx.edu.utez.sirid.model.Classroom.Classroom;
=======
>>>>>>> bda133956100d56c6e15099595aa72cdf3599592

import javax.persistence.*;

@Entity
<<<<<<< HEAD
@Table(name = "types")
@AllArgsConstructor
@NoArgsConstructor
=======
@Table(name = "type")
@NoArgsConstructor
@AllArgsConstructor
>>>>>>> bda133956100d56c6e15099595aa72cdf3599592
@Setter
@Getter
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
<<<<<<< HEAD
    @Column(unique = true, nullable = false, length = 20)
    private String name;

    @OneToOne(mappedBy = "type")
    @JsonIgnore
    private Classroom classroom;
}
=======

    @Column(unique = true,nullable = false,length = 45)
    private String name_aula;

}
>>>>>>> bda133956100d56c6e15099595aa72cdf3599592
