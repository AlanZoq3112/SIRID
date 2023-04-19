package mx.edu.utez.sirid.model.User;

import mx.edu.utez.sirid.model.Role.Role;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IUserRepository extends JpaRepository<User, Long> {
    boolean findById(long id);
    boolean existsByCorreoElectronico(String email);

    User findByCorreoElectronico(String email);
    List<User> findAll();
    Boolean existsById(String user);

    User findByRoles(Role role);

    @Modifying
    @Query(value = "UPDATE users set status =:statusUser WHERE id =:idUser",
            nativeQuery = true
    )
    Integer updateUserById(
            @Param("statusUser") Boolean statusUser,
            @Param("idUser") Long idUser
    );

    //query para solicitar la recuperacion de contraseña (contraseña olvidada)"
    @Modifying
    @Query(value = "UPDATE users set contrasena =:newContrasena,change_password=0 WHERE correo_electronico =:id",
        nativeQuery = true
    )
    Integer recoverPassword(
            @Param("newContrasena") String newContrasena,
            @Param("id") String  id
    );

    //query para cambiar la contraseña
    @Modifying
    @Query(value = "UPDATE users set contrasena =:newContrasena,change_password=1  where  correo_electronico=:id",
            nativeQuery = true
    )
    Integer changePassword(
            @Param("newContrasena") String newContrasena,
            @Param("id") String  id
    );

   //Query para ver a todos los profesores
   @Modifying
    @Query(value = "SELECT * from users WHERE role_id =3 and status= true", nativeQuery = true
   ) List<User> lookAllTeachers();

    //Query para ver a todos el personal de soporte
    @Modifying
    @Query(value = "SELECT * from users WHERE role_id =2  and status =true", nativeQuery = true
    ) List<User> lookAllSupportTeam();

    @Query(
            value = "SELECT id, change_password, contrasena, correo_electronico, (CONCAT(name,\" \",primer_apellido)) AS 'name', primer_apellido, segundo_apellido, status, academic_division_id, role_id FROM users WHERE status=true AND role_id=2 ORDER BY name ASC;",
            nativeQuery = true
    )
    List<User> selectPersonalSuport();
}

