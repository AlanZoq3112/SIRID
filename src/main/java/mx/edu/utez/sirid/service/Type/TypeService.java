package mx.edu.utez.sirid.service.Type;

import mx.edu.utez.sirid.model.Type.ITypeRepository;
import mx.edu.utez.sirid.model.Type.Type;
import mx.edu.utez.sirid.utils.CustomResponse;
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

    @Transactional(readOnly = true)
    public CustomResponse<List<Type>> getAll(){
        return new CustomResponse<>(
                this.repository.findAll(),
                false, 200, "OK"
        );
    }

    @Transactional(readOnly = true)
    public CustomResponse<Type> getOne(Long id){
        return new CustomResponse<>(
                this.repository.findById(id).get(),
                false, 200, "OK"
        );
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Type> insert(Type type) {
        if (this.repository.existsById(type.getId()))
            return new CustomResponse<>(
                    null, true, 400,
                    "El tipo ya se ha registrado"
            );
        return new CustomResponse<>(
                this.repository.saveAndFlush(type),
                false, 200,
                "Tipo registrado correctamente"
        );
    }
}
