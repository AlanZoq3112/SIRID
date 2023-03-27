package mx.edu.utez.sirid.service.Incidence;

import mx.edu.utez.sirid.model.Incidence.IIncidenceRepository;
import mx.edu.utez.sirid.model.Incidence.Incidence;
import mx.edu.utez.sirid.model.Status.Status;
import mx.edu.utez.sirid.model.User.IUserRepository;
import mx.edu.utez.sirid.model.User.User;
import mx.edu.utez.sirid.utils.inserts.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
@Transactional
public class IncidenceService {
    @Autowired
    private IIncidenceRepository repository;

    @Autowired
    private IUserRepository userRepository;

    //recuperar todas las incidenias
    @Transactional(readOnly = true)
    public CustomResponse<List<Incidence>> getAll() {
        return new CustomResponse<>(
                this.repository.findAll(),
                false, 200, "ok"
        );
    }

    //recuperar una sola incidencia
    @Transactional(readOnly = true)
    public CustomResponse<Incidence> getOne(Long id) {
        return new CustomResponse<>(
                this.repository.findById(id).get(),
                false, 200, "ok"
        );
    }

    //registrar una nueva incidencia
    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Incidence> insert(Incidence incidence) {
        if (this.repository.existsById(incidence.getId()))
            return new CustomResponse<>(
                    null, true, 400,
                    "La incidencia ya se ha registrado"
            );
        return new CustomResponse<>(
                this.repository.saveAndFlush(incidence),
                false, 200,
                "Incidencia registrada correctamente"
        );
    }

    //actualizar una incidecnia
    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Incidence> update(Incidence incidence) {
        if (!this.repository.existsById(incidence.getId()))
            return new CustomResponse<>(
                    null, true, 400,
                    "Esta incidecia no existe"
            );
        return new CustomResponse<>(
                this.repository.saveAndFlush(incidence),
                false, 200,
                "Incidencia registrada correctamente"
        );
    }

    //cambiar status de una incidencia
    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Integer> changeStatus(Incidence incidence) {
        if (!this.repository.existsById(incidence.getId()))
            return new CustomResponse<>(
                    null, true, 400,
                    "Esta incidecia no existe"
            );
        return new CustomResponse<>(
                this.repository.updateStatusById(
                        incidence.getStatus(), incidence.getId()),
                false, 200,
                "Se modifico con exito el status de la incidencia"
        );
    }

    //Historial de incidencias
    @Transactional(readOnly = true)
    public  CustomResponse<List<Incidence>> GetAllMyIncidences(User user){
        return  new CustomResponse<>(
                this.repository.findByPersonalSoporteOrDocente(user,user),
                false,200,"Historial de incidencias recuperado"
        );
    }

    //ver las incidencias en las que participa el personal de soporte de acuerdo a su status
    @Transactional(readOnly = true)
    public CustomResponse<List<Incidence>> lookIncidenceSupport(Status status,User soporte){
        System.out.println("incidence -> "+soporte.getCorreoElectronico());
        if ((!this.userRepository.existsByCorreoElectronico(soporte.getCorreoElectronico())))
        return new CustomResponse<>(
                null, true, 400,
                "Este usuario no existe"
        );

        return new CustomResponse<>(
                this.repository.lookIncidenceSupport(status,soporte),
                false,200,"Lista de incidencias recuperado con exito"
        );

    }

    //ver las incidencias en las que participa el personal de soporte de acuerdo a su status
    @Transactional(readOnly = true)
    public CustomResponse<List<Incidence>> lookIncidenceDocente(Status status,User docente){
        System.out.println("incidence -> "+docente.getCorreoElectronico());
        if ((!this.userRepository.existsByCorreoElectronico(docente.getCorreoElectronico())))
            return new CustomResponse<>(
                    null, true, 400,
                    "Este usuario no existe"
            );

        return new CustomResponse<>(
                this.repository.lookIncidenceTeacher(status, docente),
                false,200,"Lista de incidencias recuperado con exito"
        );

    }





    
}

