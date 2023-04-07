package mx.edu.utez.sirid.model.Incidence;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="resources")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Resources {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 255)
    private  String url;

    @ManyToOne
    @JoinColumn(name = "incidence_id", referencedColumnName = "id")
    @JsonIgnore
    private Incidence incidence;

}
