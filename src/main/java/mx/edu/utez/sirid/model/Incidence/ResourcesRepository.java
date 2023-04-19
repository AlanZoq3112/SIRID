package mx.edu.utez.sirid.model.Incidence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourcesRepository extends JpaRepository<Resources, Long> {
    boolean existsByName(String name);
}
