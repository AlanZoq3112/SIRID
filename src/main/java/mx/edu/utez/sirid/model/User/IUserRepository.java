package mx.edu.utez.sirid.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IUserRepository extends JpaRepository<User, Long> {
    boolean findById(long id);

    User findByCorreoElectronico(String email);

    List<User> findAll();

    @Query(value = "UPDATE User set status =: status WHERE ID =: id",
            nativeQuery = true
    )
    Boolean updateUserById(
            @Param("status") Boolean status,
            @Param("id") Long id
    );

    Boolean existsById(String user);
}

