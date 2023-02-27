package mx.edu.utez.sirid.model.Area;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAreaRepository extends JpaRepository<Area, Long> {
  boolean existsById(Long id);
}
