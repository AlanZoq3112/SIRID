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
import java.util.Locale;

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
        String firstPassword=(user.getName().substring(0,2)+user.getPrimerApellido().substring(0,2)+user.getId()).toLowerCase() ;
        System.out.println("UserService:50 ->"+firstPassword);
        user.setContrasena(encoder.encode(firstPassword));
        System.out.println("UserService:50 ->"+user.getContrasena());
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
    public CustomResponse<Boolean> changeStatus(User user) {
        if (!this.repository.existsById(user.getId()))
            return new CustomResponse<>(
                    null, true, 400,
                    "El usuario no existe"
            );
        return new CustomResponse<>(
                this.repository.updateUserById(
                        user.getStatus(), user.getId()),
                false, 200,
                "Categoría registrada correctamente"
        );
    }

    @Transactional(readOnly = true)
        public User getUserByemail(String email){
        return repository.findByCorreoElectronico(email);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Integer> changePassword(User user) {
        if (!this.repository.existsById(user.getId()))
            return new CustomResponse<>(
                    null, true, 400,
                    "El usuario no existe"
            );
        user.setContrasena(encoder.encode(user.getContrasena()));
        return new CustomResponse<>(
                this.repository.changePassword(
                        user.getContrasena(), user.getId()),
                false, 200,
                "Contraseña modificada correctamente"
        );
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Boolean> recoverPassword(User user) {
        if (!this.repository.existsById(user.getId()))
            return new CustomResponse<>(
                    null, true, 400,
                    "El usuario no existe"
            );
        String firstPassword=user.getName().substring(0,2)+user.getPrimerApellido().substring(0,2)+user.getId() ;
        return new CustomResponse<>(
                this.repository.recoverPassword(
                        firstPassword, user.getId()),
                false, 200,
                "La contraseña fue modificada correctamente"
        );

    }
}