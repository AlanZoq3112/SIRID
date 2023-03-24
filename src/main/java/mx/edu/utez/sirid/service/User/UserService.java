package mx.edu.utez.sirid.service.User;

import mx.edu.utez.sirid.model.User.IUserRepository;
import mx.edu.utez.sirid.model.User.User;
import mx.edu.utez.sirid.utils.inserts.CustomResponse;
import mx.edu.utez.sirid.utils.messages.UserMessage;
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

    @Autowired
    private JavaMailSender javaMailSender;
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
    public CustomResponse<User> insert(User user) throws MessagingException {
        if (this.repository.existsById(user.getId())) {
            return new CustomResponse<>(
                    null,
                    true,
                    400,
                    "The user has already been registered"
            );
        }
        //genera la contraseña por defaul para acceder por primera vez
        String firstPassword=(user.getName().substring(0,2)+user.getPrimerApellido().substring(0,2)+user.getId()).toLowerCase() ;
        System.out.println("UserService:insert ->"+firstPassword);
        user.setContrasena(encoder.encode(firstPassword));
        System.out.println("UserService:insert ->"+user.getContrasena());

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
    public CustomResponse<Integer> recoverPassword(User user) throws MessagingException {
        if (!this.repository.existsById(user.getId()))
            return new CustomResponse<>(
                    null, true, 400,
                    "El usuario no existe"
            );

        int numero = (int) (Math.random() * 25) + 1;
        String firstPassword=user.getName().substring(0,2)+user.getPrimerApellido().substring(0,2)+numero ;

        MimeMessage mimeMessage=javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper= new MimeMessageHelper(mimeMessage, true, "UTF-8");
        UserMessage message = new UserMessage();
        messageHelper.setTo(user.getCorreoElectronico());
        messageHelper.setFrom("20213tn014@utez.edu.mx");
        messageHelper.setSubject("Se ha cambiado tu contraseña");
        messageHelper.setText(message.recoverAccount(user.getName(), firstPassword),true);
        this.javaMailSender.send(mimeMessage);

        firstPassword= encoder.encode(firstPassword);

        return new CustomResponse<>(
                this.repository.recoverPassword(
                        firstPassword, user.getId()),
                false, 200,
                "Se envio la contraseña temporal a su cuenta"
        );

    }
}