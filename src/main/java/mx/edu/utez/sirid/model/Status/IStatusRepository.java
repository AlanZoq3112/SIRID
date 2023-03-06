package mx.edu.utez.sirid.model.Status;


import mx.edu.utez.sirid.enums.EStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStatusRepository extends JpaRepository<Status, Long>{
    boolean existsById(Long id);
    boolean existsByName(EStatus name);

}
