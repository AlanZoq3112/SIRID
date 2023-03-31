package mx.edu.utez.sirid.service.User;

import mx.edu.utez.sirid.model.Role.Role;
import mx.edu.utez.sirid.model.User.IUserRepository;
import mx.edu.utez.sirid.model.User.User;
import mx.edu.utez.sirid.security.jwt.JwtEntryPoint;
import mx.edu.utez.sirid.utils.inserts.CustomResponse;
import mx.edu.utez.sirid.utils.messages.UserMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.sql.SQLException;
import java.util.List;

@Service
@Transactional
public class UserService {
    private final static Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private IUserRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    @Transactional(readOnly = true)
    public CustomResponse<List<User>> getALll(Role role) {
        return new CustomResponse<>(
                this.repository.findAll(),
                false,
                200,
                "Ok"
        );
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<User> getOne(String email) {
        return new CustomResponse<>(
                this.repository.findByCorreoElectronico(email),
                false, 200, "Ok"
        );
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<User> insert(User user) throws MessagingException {
        if (this.repository.existsByCorreoElectronico(user.getCorreoElectronico())) {
            return new CustomResponse<>(
                    null,
                    true,
                    400,
                    "The user has already been registered"
            );
        }

        //genera la contraseña por default para acceder por primera vez
        int numero = (int) (Math.random() * 25) + 1;
        String firstPassword=(user.getName().substring(0,3)+user.getPrimerApellido().substring(0,3)+numero).toLowerCase();
        user.setContrasena(encoder.encode(firstPassword));

        //envio de correos electronicos
        MimeMessage mimeMessage=javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper= new MimeMessageHelper(mimeMessage, true, "UTF-8");
        UserMessage message = new UserMessage();
        messageHelper.setTo(user.getCorreoElectronico());
        messageHelper.setFrom("20213tn014@utez.edu.mx");
        messageHelper.setSubject("Confirmación: tu cuenta ha sido creada");
        messageHelper.setText(message.newAccount(user.getName(), firstPassword),true);
        this.javaMailSender.send(mimeMessage);

        return new CustomResponse<>(
                this.repository.saveAndFlush(user),
                false,
                200,
                "User registered succesfully"
        );
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<User> update(User user) {
        if (!this.repository.existsByCorreoElectronico(user.getCorreoElectronico()))
            return new CustomResponse<>(
                    null,
                    true,
                    400,
                    "Este usuario no existe"
            );
        user.setContrasena(encoder.encode(user.getContrasena()));
        return new CustomResponse<>(
                this.repository.saveAndFlush(user),
                false,
                200,
                "El usuario se actualizo con exito"
        );
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Boolean> changeStatus(User user) {
        if (!this.repository.existsByCorreoElectronico(user.getCorreoElectronico()))
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
        if (!this.repository.existsByCorreoElectronico(user.getCorreoElectronico()))
            return new CustomResponse<>(
                    null, true, 400,
                    "El usuario no existe"
            );
        user.setContrasena(encoder.encode(user.getContrasena()));
        System.out.println("new password->"+user.getContrasena());

        return new CustomResponse<>(
                this.repository.changePassword(
                        user.getContrasena(), user.getId()),
                false, 200,
                "Contraseña modificada correctamente"
        );
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Integer> recoverPassword(User user) throws MessagingException {
        if (!this.repository.existsByCorreoElectronico(user.getCorreoElectronico()))
            return new CustomResponse<>(
                    null, true, 400,
                    "El usuario no existe"
            );

        int numero = (int) (Math.random() * 25) + 1;
        String newPassword=user.getName().substring(0,2)+user.getPrimerApellido().substring(0,2)+numero ;

        MimeMessage mimeMessage=javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper= new MimeMessageHelper(mimeMessage, true, "UTF-8");
        UserMessage message = new UserMessage();
        messageHelper.setTo(user.getCorreoElectronico());
        messageHelper.setFrom("20213tn014@utez.edu.mx");
        messageHelper.setSubject("Se ha cambiado tu contraseña");
        messageHelper.setText(message.recoverAccount(user.getName(), newPassword),true);
        this.javaMailSender.send(mimeMessage);

        newPassword= encoder.encode(newPassword);

        return new CustomResponse<>(
                this.repository.recoverPassword(
                        newPassword, user.getId()),
                false, 200,
                "Se envio la contraseña temporal a su cuenta"
        );

    }


}