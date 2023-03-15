package mx.edu.utez.sirid.model.Incidence;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IIncidenceRepository extends JpaRepository<Incidence, Long> {
    @Query(
            value = "UPDATE incidences SET status = :status " +
                    "WHERE id = :id",
            nativeQuery = true
    )
    boolean updateStatusById(
            @Param("status") Boolean status,
            @Param("id") Long id
    );

    /*@Query(
            value = "UPDATE incidences SET asigned_at = :asigned_at " +
                    "WHERE id = :id",
            nativeQuery = true
    )
    boolean updateUserByRole(
            @Param("role_id") Long role_id
    );*/



    boolean existsById(Long id);
}
