package mx.edu.utez.sirid.model.User;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.sirid.model.AcademicDivision.AcademicDivision;
import mx.edu.utez.sirid.model.Incidence.Incidence;
import mx.edu.utez.sirid.model.Role.Role;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
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

    @Column( nullable = false)
    private Boolean changePassword;


    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false, referencedColumnName = "id")
    private Role roles;

    @ManyToOne
    @JoinColumn(name = "academicDivision_id", nullable = false, referencedColumnName = "id")
    private  AcademicDivision academicDivision;

    @OneToMany(mappedBy = "docente")
    @JsonIgnore
    private List<Incidence> incidenceListTeacher;

    @OneToMany(mappedBy = "personalSoporte")
    @JsonIgnore
    private List<Incidence>incidenceListSupport;

    @OneToMany(mappedBy = "user")
    private List<TokenMovil> tokenMovilList;






}
