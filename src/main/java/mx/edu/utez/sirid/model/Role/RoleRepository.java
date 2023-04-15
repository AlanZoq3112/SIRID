package mx.edu.utez.sirid.model.Role;


import mx.edu.utez.sirid.model.Area.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    boolean existsById(Long id);

    boolean existsByName(String name);

    boolean findById(long Id);

    @Query(
            value = "select * from roles ORDER BY name ASC;",
            nativeQuery = true
    )
    List<Role> selectRoles();
}
