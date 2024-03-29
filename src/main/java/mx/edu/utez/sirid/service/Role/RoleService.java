package mx.edu.utez.sirid.service.Role;

import mx.edu.utez.sirid.model.Role.Role;
import mx.edu.utez.sirid.model.Role.RoleRepository;
import mx.edu.utez.sirid.utils.inserts.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
@Transactional
public class RoleService {
    @Autowired
    private RoleRepository repository;

    //trae todos
    @Transactional(readOnly = true)
    public CustomResponse<List<Role>> getAll(){
        return new CustomResponse<>(
                this.repository.findAll(),
                false,
                200,
                "Ok"
        );
    }

    //trae uno
    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Role> getOne(Long id){
        return new CustomResponse<>(
                this.repository.findById(id).get(),
                false,200,"OK"
        );
    }

    //inserta uno nuevo
    @Transactional(rollbackFor =  {SQLException.class})
    public CustomResponse<Role> insert(Role role){
        if(this.repository.existsByName(role.getName()))
            return new CustomResponse<>(
                    null,
                    true,
                    400,
                    "The role has already been registered"
            );
        return new CustomResponse<>(
                this.repository.saveAndFlush(role),
                false,
                200,
                "Role registered successfully"
        );
    }

    //trae todos en orden alfabetico
    @Transactional(readOnly = true)
    public CustomResponse<List<Role>> selectRole(){
        return new CustomResponse<>(
                this.repository.selectRoles(),
                false,
                200,
                "Ok"
        );
    }
}
