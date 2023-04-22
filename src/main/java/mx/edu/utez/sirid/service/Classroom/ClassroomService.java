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

    //trae todos
    @Transactional(readOnly = true)
    public CustomResponse<List<Classroom>> getAll(){
        return new CustomResponse<>(
                this.repository.findAll(),
                false, 200, "OK"
        );
    }

    //trae uno
    @Transactional(readOnly = true)
    public CustomResponse<Classroom> getOne(Long id){
        return new CustomResponse<>(
                this.repository.findById(id).get(),
                false, 200, "OK"
        );
    }

    //inserta uno
    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Classroom> insert(Classroom classroom) {
        if (this.repository.existsByName(classroom.getName()))
            return new CustomResponse<>(
                    null, true, 400,
                    "Esta aula ya ha sido registrada"
            );
        return new CustomResponse<>(
                this.repository.saveAndFlush(classroom),
                false, 200,
                "Aula registrada correctamente"
        );
    }

    //actualiza uno
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

    //cambia el status
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

    //trae todos los activos ordenados alfabeticamente
    @Transactional(readOnly = true)
    public CustomResponse<List<Classroom>> selectClassroms(){
        return new CustomResponse<>(
                this.repository.selectClassroms(),
                false, 200, "OK"
        );
    }
}
