package mx.edu.utez.sirid.model.Area;


import mx.edu.utez.sirid.enums.Areas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAreaRepository extends JpaRepository<Area,Long> {
    boolean findById(long Id);

    boolean existsByName(Areas name);
    List<Area> findAll();


}
