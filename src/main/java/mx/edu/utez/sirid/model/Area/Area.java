package mx.edu.utez.sirid.model.Area;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.sirid.enums.Areas;
import mx.edu.utez.sirid.model.Classroom.Classroom;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="areas")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Area {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(unique = true,nullable = false,length = 25)
    private Areas name;

    public Area(Areas name){
        this.name=name;
    }


    @OneToMany(mappedBy = "area")
    @JsonIgnore
    private Set<Classroom> classroom;



}
