package mx.edu.utez.sirid.model.Incidence;

import mx.edu.utez.sirid.model.Status.Status;
import mx.edu.utez.sirid.model.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IIncidenceRepository extends JpaRepository<Incidence, Long> {

    boolean existsById(Long id);

    //cambio de status
    @Modifying
    @Query(
            value = "UPDATE incidences SET id_status =:status ,last_modify=now(),finish_at =now() WHERE id =:id",
            nativeQuery = true
    )
    Integer updateStatusById(@Param("status") Status status, @Param("id") Long id);

    //historial de incidecnias
    List<Incidence> findByPersonalSoporteOrDocente(User Teacher, User Suport);

    //ver incidencias de acuerdo a su status y el personal de soporte involucrado
    @Query(
            value = "select * from incidences where id_status=:status AND asigned_at=:support",
            nativeQuery = true
    )
    List<Incidence> lookIncidenceSupport( @Param("status") Status status, @Param("support") User soporte);

    //ver incidencias de acuerdo a su status y el docente involucrado
    @Query(
            value = "select * from incidences where id_status=:status AND created_at=:docente",
            nativeQuery = true
    )
    List<Incidence> lookIncidenceTeacher(@Param("status") Status status, @Param("docente") User Docente);

    //Cambio del personal de soporte
    @Modifying
    @Query(
            value = "Update incidences set asigned_at=:support where id=:id;",nativeQuery = true
    )
    Integer changePersonalSupport(@Param("support") User support,
                                  @Param("id") Long id);



}