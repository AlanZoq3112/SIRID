package mx.edu.utez.sirid.model.Classroom;

import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface IClassroomRepository extends JpaRepository<Classroom, Long> {

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


    boolean existsById(Long id);

    boolean existsByName(String name);
}