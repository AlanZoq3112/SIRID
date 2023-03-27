package mx.edu.utez.sirid.model.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import mx.edu.utez.sirid.model.Classroom.Classroom;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "types")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(unique = true,nullable = false,length = 45)
    private String name;

    public Type(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "type")
    @JsonIgnore
    private Set<Classroom> classroom;

}