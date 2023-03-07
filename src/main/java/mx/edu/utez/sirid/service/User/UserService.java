package mx.edu.utez.sirid.service.User;

import mx.edu.utez.sirid.model.User.IUserRepository;
import mx.edu.utez.sirid.model.User.User;
import mx.edu.utez.sirid.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.sql.SQLException;
import java.util.List;
@Service
@Transactional
public class UserService {
    @Autowired
    private IUserRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    @Transactional(readOnly = true)
    public CustomResponse<List<User>> getALll() {
        return new CustomResponse<>(
                this.repository.findAll(),
                false,
                200,
                "Ok"
        );
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<User> getOne(Long id) {
        return new CustomResponse<>(
                this.repository.findById(id).get(),
                false, 200, "Ok"
        );
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<User> insert(User user) {
        if (this.repository.existsById(user.getId())) {
            return new CustomResponse<>(
                    null,
                    true,
                    400,
                    "The user has already been registered"
            );
        }

//        user.setContraseña(encoder.encode(user.getContraseña())
//        );
        return new CustomResponse<>(
                this.repository.saveAndFlush(user),
                false,
                200,
                "User registered succesfully"
        );
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<User> update(User user) {
        if (!this.repository.existsById(user.getId()))
            return new CustomResponse<>(
                    null,
                    true,
                    400,
                    "Este usuario no existe"
            );
        return new CustomResponse<>(
                this.repository.saveAndFlush(user),
                false,
                200,
                "Usuario registrado con exito"
        );
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Boolean> changeStatus(User user){
        if (!this.repository.existsById(user.getId()))
            return new CustomResponse<>(
                    null,
                    true,
                    400,
                    "El usuario no existe"
            );
        return new CustomResponse<>(
                this.repository.existsById(user.getId()),
                false,
                200,
                "Usuario eliminado con exito"
        );
    }

    @Transactional(readOnly = true)
        public User getUserByemail(String email){
        return repository.findByCorreoElectronico(email);
    }
}