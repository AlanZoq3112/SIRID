package mx.edu.utez.sirid.model.Status;



import mx.edu.utez.sirid.model.Role.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IStatusRepository extends JpaRepository<Status, Long>{
    boolean existsById(Long id);
    boolean existsByName(String name);

    @Query(
            value = "select * from status ORDER BY name ASC;",
            nativeQuery = true
    )
    List<Status> selectstatus();

}
