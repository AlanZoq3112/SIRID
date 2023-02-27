package mx.edu.utez.sirid.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.sirid.model.AcademicDivision.AcademicDivision;
import mx.edu.utez.sirid.model.Role.Role;

import javax.persistence.*;
import java.util.List;

    @Entity
    @Table(name = "user")
    @NoArgsConstructor
    @AllArgsConstructor
    @Setter
    @Getter
    public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(unique = true, nullable = false, length = 120 )
        private String name;

        @Column(unique = true, nullable = false, length = 45 )
        private String primer_apellido;

        @Column(unique = true, nullable = false, length = 45 )
        private String segundo_apellido;

        @Column(unique = true, nullable = false, length = 45 )
        private String correo_electronico;

        @Column(unique = true, nullable = false, columnDefinition = "Text")
        private String uid;

        @Column(unique = true, nullable = false, length = 12)
        private String contraseña;

        @Column(nullable = false, columnDefinition = "TINYINT DEFAULT 1")
        private Boolean status;

        @Column(unique = false, nullable = false, length = 0)
        private Boolean changePassword;

        @OneToOne
        @JoinColumn(name = "role_id", nullable = false, referencedColumnName = "id")
        private Role role;

        @OneToOne
        @JoinColumn(name = "academicDivision_id", nullable = false, referencedColumnName = "id")
        private  AcademicDivision academicDivision;
       }

