package mx.edu.utez.sirid.model.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.sirid.model.AcademicDivision.AcademicDivision;
import mx.edu.utez.sirid.model.Role.Role;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column( nullable = false, length = 120 )
    private String name;

    @Column( nullable = false, length = 45 )
    private String primerApellido;

    @Column( length = 45 )
    private String segundoApellido;

    @Column(unique = true, nullable = false, length = 45 )
    private String correoElectronico;

    @Column( nullable = false)
    private String contrasena;

    @Column(nullable = false, columnDefinition = "TINYINT DEFAULT 1")
    private Boolean status;

    @Column( nullable = false, length = 0)
    private Boolean changePassword;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false, referencedColumnName = "id")
    private Role roles;

    @ManyToOne
    @JoinColumn(name = "academicDivision_id", nullable = false, referencedColumnName = "id")
    private  AcademicDivision academicDivision;

    //private String firstPassword=name.substring(0,2)+primerApellido.substring(0,2)+id;

}
