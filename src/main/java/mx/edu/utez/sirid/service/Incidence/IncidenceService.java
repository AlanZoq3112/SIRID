package mx.edu.utez.sirid.service.Incidence;


import mx.edu.utez.sirid.model.Classroom.Classroom;
import mx.edu.utez.sirid.model.Incidence.IIncidenceRepository;
import mx.edu.utez.sirid.model.Incidence.Incidence;
import mx.edu.utez.sirid.utils.CustomResponse;
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

    @Transactional(readOnly = true)
    public CustomResponse<List<Incidence>> getAll(){
        return  new CustomResponse<>(
                this.repository.findAll(),
                false, 200, "ok"
        );
    }

    @Transactional(readOnly = true)
    public CustomResponse<Incidence> getOne(Long id){
        return new CustomResponse<>(
                this.repository.findById(id).get(),
                false, 200, "ok"
        );
    }

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

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Incidence> update(Incidence incidence) {
        if (!this.repository.existsById(incidence.getId()))
            return new CustomResponse<>(
                    null, true, 400,
                    "El incidencia no existe"
            );
        return new CustomResponse<>(
                this.repository.saveAndFlush(incidence),
                false, 200,
                "Incidencia registrada correctamente"
        );
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Boolean> changeStatus(Classroom classroom) {
        if (!this.repository.existsById(classroom.getId()))
            return new CustomResponse<>(
                    null, true, 400,
                    "El incidencia no existe"
            );
        return new CustomResponse<>(
                this.repository.updateStatusById(
                        classroom.getStatus(), classroom.getId()),
                false, 200,
                "Incidencia registrada correctamente"
        );
    }


}
