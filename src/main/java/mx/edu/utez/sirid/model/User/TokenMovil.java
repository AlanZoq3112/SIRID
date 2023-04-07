package mx.edu.utez.sirid.model.User;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "tokenNotification")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class TokenMovil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 250)
    private String tokenNotification;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false,referencedColumnName = "id")
    @JsonIgnore
    private User user;
}
