package mx.edu.utez.sirid.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
    boolean findById(long id);

    List<User> findAll();

    @Query(value = "UPDATE User set status =: status WHERE ID =: id",
    nativeQuery = true
    )
    
    Boolean updateUserById(
            @Param("status") Boolean status,
            @Param("id") Long id
    );
    Boolean existsUsersBy(String user);
}
