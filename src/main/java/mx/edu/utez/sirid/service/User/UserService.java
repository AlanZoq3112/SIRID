package mx.edu.utez.sirid.service.User;

import mx.edu.utez.sirid.model.Role.Role;
import mx.edu.utez.sirid.model.User.IUserRepository;
import mx.edu.utez.sirid.model.User.User;
import mx.edu.utez.sirid.security.jwt.JwtEntryPoint;
import mx.edu.utez.sirid.utils.inserts.CreateRoles;
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
    public CustomResponse<List<User>> getALll() {
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
        int numero = (int) (Math.random() * 115) + 1;
        String letters[]={"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
        int ramdom1 = (int) (Math.random() * 51);
        int ramdom2 = (int) (Math.random() * 51);
        int ramdom3 = (int) (Math.random() * 51);
        int ramdom4 = (int) (Math.random() * 51);
        int ramdom5 = (int) (Math.random() * 51);
        int ramdom6 = (int) (Math.random() * 51);
        int ramdom7 = (int) (Math.random() * 51);
        int ramdom8 = (int) (Math.random() * 51);
        String firstPassword=letters[ramdom1]+letters[ramdom2]+letters[ramdom3]+letters[ramdom4]+letters[ramdom5]+letters[ramdom6]+letters[ramdom7]+letters[ramdom8]+numero;
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
    public CustomResponse<Integer> changeStatus(User user) {
        System.out.println("118 -> "+ user.getCorreoElectronico());
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
        System.out.println(email);
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
                        user.getContrasena(), user.getCorreoElectronico()),
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
        User user1=this.getUserByemail(user.getCorreoElectronico());
        int numero = (int) (Math.random() * 115) + 1;
        String letters[]={"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
        int ramdom1 = (int) (Math.random() * 51);
        int ramdom2 = (int) (Math.random() * 51);
        int ramdom3 = (int) (Math.random() * 51);
        int ramdom4 = (int) (Math.random() * 51);
        int ramdom5 = (int) (Math.random() * 51);
        int ramdom6 = (int) (Math.random() * 51);
        int ramdom7 = (int) (Math.random() * 51);
        int ramdom8 = (int) (Math.random() * 51);
        String newPassword=letters[ramdom1]+letters[ramdom2]+letters[ramdom3]+letters[ramdom4]+letters[ramdom5]+letters[ramdom6]+letters[ramdom7]+letters[ramdom8]+numero;
        MimeMessage mimeMessage=javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper= new MimeMessageHelper(mimeMessage, true, "UTF-8");
        UserMessage message = new UserMessage();
        messageHelper.setTo(user.getCorreoElectronico());
        messageHelper.setFrom("20213tn014@utez.edu.mx");
        messageHelper.setSubject("Se ha cambiado tu contraseña");
        messageHelper.setText(message.recoverAccount(user1.getName(), newPassword),true);
        this.javaMailSender.send(mimeMessage);
        System.out.println("newPassword" + newPassword);
        newPassword= encoder.encode(newPassword);
        System.out.println("newPassword" + newPassword);
        return new CustomResponse<>(
                this.repository.recoverPassword(
                        newPassword, user.getCorreoElectronico()),
                false, 200,
                "Se envio la contraseña temporal a su cuenta"
        );

    }

    @Transactional(readOnly = true)
    public  CustomResponse<List<User>> enablePersonalSupport(){
        return  new CustomResponse<>(
                this.repository.lookAllSupportTeam(),false,200,"Personal de Soporte Activo"
        );
    }

    @Transactional(readOnly = true)
    public  CustomResponse<List<User>> enableTeachers(){
        return  new CustomResponse<>(
                this.repository.lookAllTeachers(), false,200,"Docentes Activos"
        );
    }
    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Boolean> requestChanges(User user) throws MessagingException {
        Role admin= new Role((long) 1,"SuperAdmin",null);
        User superAdmin = repository.findByRoles(admin);
        MimeMessage mimeMessage=javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper= new MimeMessageHelper(mimeMessage, true, "UTF-8");
        UserMessage message = new UserMessage();
        messageHelper.setTo(superAdmin.getCorreoElectronico());
        messageHelper.setFrom("20213tn014@utez.edu.mx");
        messageHelper.setSubject("tienes una nueva solicitud de Cambios para el usuario "+user.getName()+" "+user.getPrimerApellido());
        messageHelper.setText(message.requestChanges(superAdmin.getName(),user),true);
        this.javaMailSender.send(mimeMessage);

        return new CustomResponse<>(
                null,
                false, 200,
                "Se Envio la solicitud de Cambios"
        );


    }

    @Transactional(readOnly = true)
    public CustomResponse<List<User>> selectPersonalSupport() {
        return new CustomResponse<>(
                this.repository.selectPersonalSuport(),
                false,
                200,
                "Ok"
        );
    }


}