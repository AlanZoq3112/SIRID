package mx.edu.utez.sirid.service.Type;

import mx.edu.utez.sirid.model.Type.ITypeRepository;
import mx.edu.utez.sirid.model.Type.Type;
import mx.edu.utez.sirid.utils.inserts.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
@Transactional
public class TypeService {
    @Autowired
    private ITypeRepository repository;


    // trae todos
    @Transactional(readOnly = true)
    public CustomResponse<List<Type>> getAll(){
        return  new CustomResponse<>(
                this.repository.findAll(),
                false,
                200,
                "ok"
        );
    }

    //trae uno
    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Type> getOne(Long id){
        return new CustomResponse<>(
                this.repository.findById(id).get(),
                false,200,"OK"
        );
    }

    //inserta uno
    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Type> insert(Type type) {
        if (this.repository.existsByName(type.getName()))
            return new CustomResponse<>(
                    null, true, 400,
                    "Este tipo de Usuario ya se encuentra registrado "
            );
        return new CustomResponse<>(
                this.repository.saveAndFlush(type),
                false, 200,
                "Tipo de usuario  registrado correctamente");
    }

    //trea todos en orden alfabetico
    @Transactional(readOnly = true)
    public CustomResponse<List<Type>> selectType(){
        return  new CustomResponse<>(
                this.repository.selectTypes(),
                false,
                200,
                "ok"
        );
    }
}
