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
    private String url;
    @Transient
    private String filebase64;
    private String name;
    private String mimeType;
    @ManyToOne
    @JoinColumn(name = "incidence_id",  referencedColumnName= "id",nullable = false)
    @JsonIgnore
    private Incidence incidence;
}



