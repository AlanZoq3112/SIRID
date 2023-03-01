package mx.edu.utez.sirid.model.Classroom;

import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface IClassroomRepository extends JpaRepository<Classroom, Long> {

    @Query(
            value = "UPDATE classroom SET status = :status " +
                    "WHERE id = :id",
            nativeQuery = true
    )
    boolean updateStatusById(
            @Param("status") Boolean status,
            @Param("id") Long id
    );


    boolean existsById(Long id);
}
