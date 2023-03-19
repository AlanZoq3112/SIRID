package mx.edu.utez.sirid.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IUserRepository extends JpaRepository<User, Long> {
    boolean findById(long id);
    User findByCorreoElectronico(String email);
    List<User> findAll();
    Boolean existsById(String user);

    @Modifying
    @Query(value = "UPDATE User set status =: status WHERE ID =: id",
            nativeQuery = true
    )
    Boolean updateUserById(
            @Param("status") Boolean status,
            @Param("id") Long id
    );

    //query para solicitar la recuperacion de contraseña (contraseña olvidada)"
    @Modifying
    @Query(value = "UPDATE User set contrasena =:newContrasena,changeStatus=:0 WHERE id =:id",
        nativeQuery = true
    )
    Boolean recoverPassword(
            @Param("newContrasena") String newContrasena,
            @Param("id") Long id
    );

    //query para cambiar la contraseña ****Ya funciona****
    @Modifying
    @Query(value = "UPDATE users set contrasena =:newContrasena,change_password=1 WHERE id =:id",
            nativeQuery = true
    )
    Integer changePassword(
            @Param("newContrasena") String newContrasena,
            @Param("id") Long id
    );

   //Query para ver a todos los profesores
   @Modifying
    @Query(value = "SELECT * from USERS WHERE role_id =: 3",
           nativeQuery = true
   )
   List<User> lookAllTeachers();

    //Query para ver a todos el personal de soporte
    @Modifying
    @Query(value = "SELECT * from USERS WHERE role_id =: 2",
            nativeQuery = true
    )
    List<User> lookAllSupportTeam();





}

