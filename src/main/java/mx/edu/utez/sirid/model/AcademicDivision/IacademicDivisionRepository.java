package mx.edu.utez.sirid.model.AcademicDivision;


import mx.edu.utez.sirid.enums.DivisionesAcademicas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IacademicDivisionRepository extends JpaRepository<AcademicDivision, Long> {
boolean existsById(Long id);

boolean existsByName(DivisionesAcademicas name);
}
