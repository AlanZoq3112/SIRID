package mx.edu.utez.sirid.model.Area;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAreaRepository extends JpaRepository<Area,Long> {
    boolean findById(long Id);

    boolean existsByName(String name);
    List<Area> findAll();


}
