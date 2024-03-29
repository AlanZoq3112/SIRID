package mx.edu.utez.sirid.model.Classroom;

import jdk.jfr.Registered;
import mx.edu.utez.sirid.model.AcademicDivision.AcademicDivision;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IClassroomRepository extends JpaRepository<Classroom, Long> {

    //Cambia el estatus de los salones
   @Modifying
    @Query(
            value = "UPDATE classrooms SET status =:status " +
                    "WHERE id = :id",
            nativeQuery = true
    )
    int updateStatusById(
            @Param("status") Boolean status,
            @Param("id") Long id
    );

   //trae los salones en orden division academica -> salon ordenados alfabeticamente
   @Query(
          value = " select c.id,CONCAT(a.name,' ',c.name) AS 'name',c.status,c.area_id,c.type_id from classrooms c inner join areas a on c.area_id=a.id where status=true ORDER BY c.name ASC",
          nativeQuery = true
  )
   List<Classroom> selectClassroms();


    boolean existsById(Long id);

    boolean existsByName(String name);

    @Query(
            value = "SELECT COUNT(id_classroom) FROM incidences where id_classroom=:id",
            nativeQuery = true
    )
    Long incidencesForClassroom(@Param("id") Long idClassroom);

}