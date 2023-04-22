package mx.edu.utez.sirid.model.AcademicDivision;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IacademicDivisionRepository extends JpaRepository<AcademicDivision, Long> {
    boolean existsById(Long id);

    boolean existsByName(String name);


    //Este query solo trae las Divisiones academicas ordenadas alfabeticamente
    @Query(
            value = "select * from academic_divisions ORDER BY name ASC;",
            nativeQuery = true
    )
    List<AcademicDivision> selectAcademies();

}
