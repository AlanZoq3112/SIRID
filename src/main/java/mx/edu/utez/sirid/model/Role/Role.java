package mx.edu.utez.sirid.model.Role;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.sirid.enums.Roles;
import mx.edu.utez.sirid.model.User.User;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "roles")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(unique = true,nullable = false, length = 20)
    private Roles name;

    public Role(Roles roles) {
        this.name = roles;
    }

    @ManyToMany(mappedBy = "roles")
    @JsonIgnore
    private Set<User> user;

}
