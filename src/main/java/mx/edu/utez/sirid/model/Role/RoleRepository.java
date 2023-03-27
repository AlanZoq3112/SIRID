package mx.edu.utez.sirid.model.Role;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    boolean existsById(Long id);

    boolean existsByName(String name);

    boolean findById(long Id);
}
