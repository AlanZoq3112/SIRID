package mx.edu.utez.sirid.service.Classroom;

import mx.edu.utez.sirid.model.Classroom.Classroom;
import mx.edu.utez.sirid.model.Classroom.IClassroomRepository;
import mx.edu.utez.sirid.utils.inserts.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
@Transactional
public class ClassroomService {
    @Autowired
    private IClassroomRepository repository;

    @Transactional(readOnly = true)
    public CustomResponse<List<Classroom>> getAll(){
        return new CustomResponse<>(
                this.repository.findAll(),
                false, 200, "OK"
        );
    }

    @Transactional(readOnly = true)
    public CustomResponse<Classroom> getOne(Long id){
        return new CustomResponse<>(
                this.repository.findById(id).get(),
                false, 200, "OK"
        );
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Classroom> insert(Classroom classroom) {
        if (this.repository.existsById(classroom.getId()))
            return new CustomResponse<>(
                    null, true, 400,
                    "La aula ya se ha registrado"
            );
        return new CustomResponse<>(
                this.repository.saveAndFlush(classroom),
                false, 200,
                "Aula registrada correctamente"
        );
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Classroom> update(Classroom classroom) {
        if (!this.repository.existsById(classroom.getId()))
            return new CustomResponse<>(
                    null, true, 400,
                    "El aula no existe"
            );
        return new CustomResponse<>(
                this.repository.saveAndFlush(classroom),
                false, 200,
                "Aula actualizada "
        );
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Integer> changeStatus(Classroom classroom) {
        if (!this.repository.existsById(classroom.getId()))
            return new CustomResponse<>(
                    null, true, 400,
                    "El estatus del aula no se pudo actualizar o no existe"
            );
        return new CustomResponse<>(
                this.repository.updateStatusById(
                        classroom.getStatus(), classroom.getId()),
                false, 200,
                "Estatus del Aula modificado con exito"
        );
    }
}
