package mx.edu.utez.sirid.model.Role;

import mx.edu.utez.sirid.enums.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    boolean existsById(Long id);

    boolean existsByName(Roles name);

    boolean findById(long Id);
}
