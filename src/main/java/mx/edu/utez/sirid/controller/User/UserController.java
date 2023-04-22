package mx.edu.utez.sirid.controller.User;
import mx.edu.utez.sirid.controller.Role.dtos.RoleDTO;
import mx.edu.utez.sirid.controller.User.dtos.UserDTO;
import mx.edu.utez.sirid.model.Incidence.Incidence;
import mx.edu.utez.sirid.model.User.User;
import mx.edu.utez.sirid.service.Incidence.IncidenceService;
import mx.edu.utez.sirid.service.User.UserService;
import mx.edu.utez.sirid.utils.inserts.CustomResponse;
import mx.edu.utez.sirid.utils.messages.UserMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api-sirid/user/")
@CrossOrigin(origins = {"*"})
public class UserController {

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private UserService userService;

    @Autowired
    private IncidenceService incidenceService;

    //Ver a todos los usuario
    @GetMapping("/")
    public ResponseEntity<CustomResponse<List<User>>> getAll(){
        return new ResponseEntity<>(
                this.userService.getALll(),
                HttpStatus.OK
        );
    }

    //Ver solamente un usuario
    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<User>> getOne(@PathVariable("id") String email){
        return new ResponseEntity<>(
                this.userService.getOne(email),
                HttpStatus.OK
        );
    }

    //Crear un usuario
    @PostMapping("/")
    public ResponseEntity<CustomResponse<User>> insert(@RequestBody UserDTO userDTO, @Valid BindingResult result) throws MessagingException {
        if (result.hasErrors()) {
            return new ResponseEntity<>(
                    null,
                    HttpStatus.BAD_REQUEST
            );
        }
        return new ResponseEntity<>(
                this.userService.insert(userDTO.getUser()),
                HttpStatus.CREATED
        );
    }

    //Actualizar usuario (solo actualiza el superAdmin, para que el usuario pueda actualizar sus propios datos hacer referencia al requestchanges)
    @PutMapping("/")
    public ResponseEntity<CustomResponse<User>> update( @Valid @RequestBody UserDTO userDTO, BindingResult result
    ) {
        return new ResponseEntity<>(
                this.userService.update(userDTO.getUser()),
                HttpStatus.OK);
    }

    //Cambiar status del usuario
    @PatchMapping("/")
    public ResponseEntity<CustomResponse<Integer>> changeStatus( @RequestBody UserDTO userDTO
    ) {
        return new ResponseEntity<>(
                this.userService.changeStatus(userDTO.getUser()),
                HttpStatus.OK
        );
    }

    //recuperar cuenta
    @PatchMapping("/recoverPassword")
    public ResponseEntity<CustomResponse<Integer>> recoverpasword( @Valid @RequestBody UserDTO userDTO, BindingResult result) throws MessagingException
    {
        return new ResponseEntity<>(
                this.userService.recoverPassword(userDTO.getUser()),
                HttpStatus.OK);
    }

    //modificar contraseña
    @PatchMapping("/changePassword")
    public ResponseEntity<CustomResponse<Integer>> changePassword(@RequestBody UserDTO userDTO, @Valid BindingResult result) throws MessagingException
    {
        if (result.hasErrors()) {
            return new ResponseEntity<>(
                    null,
                    HttpStatus.BAD_REQUEST
            );
        }

        MimeMessage mimeMessage=javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper= new MimeMessageHelper(mimeMessage, true, "UTF-8");
        UserMessage message = new UserMessage();
        messageHelper.setTo(userDTO.getCorreo_electronico());
        messageHelper.setFrom("20213tn014@utez.edu.mx");
        messageHelper.setSubject("Contraseña modificada");
        messageHelper.setText(message.changePassWord(),true);
        this.javaMailSender.send(mimeMessage);
        return new ResponseEntity<>(
                this.userService.changePassword(userDTO.getUser()),
                HttpStatus.OK
        );
    }

    //recuperar historial de incidencias
    @GetMapping("/history")
    public ResponseEntity<CustomResponse<List<Incidence>>> getMyHistory(@RequestBody UserDTO userDTO){
        return  new ResponseEntity<>(
                this.incidenceService.GetAllMyIncidences(userDTO.getUser()),
                HttpStatus.OK
        );
    }

    //Trae a todo el personal de soporte activo(usar este para los selects)
    @GetMapping("/users/support")
    public  ResponseEntity<CustomResponse<List<User>>> enablePersonalSupport(){
        return  new ResponseEntity<>(
                this.userService.enablePersonalSupport(),
                HttpStatus.OK
        );
    }

    //Trae a todos los docentes activos
    @GetMapping("/users/teachers")
    public  ResponseEntity<CustomResponse<List<User>>> enableTeachers(){
        return  new ResponseEntity<>(
                this.userService.enableTeachers(),
                HttpStatus.OK
        );
    }

    //Solicitud de cambios para los usuarios(falta probar pero eso ya es en el servidor)
    @GetMapping("/requestChanges")
    public  ResponseEntity<CustomResponse<Boolean>> requestChanges(@Valid @RequestBody UserDTO userDTO,BindingResult result) throws MessagingException {
        if (result.hasErrors())
            return  new ResponseEntity<>(
                    null,
                    HttpStatus.BAD_REQUEST
            );
        return  new ResponseEntity<>(
                this.userService.requestChanges(userDTO.getUser()),
                HttpStatus.CREATED
        );
    }

    //hace lo mismo que el de enable personal support, no me di cuneta que lo duplique xd
    @GetMapping("/select")
    public ResponseEntity<CustomResponse<List<User>>> selectPersonalSupport(){
        return new ResponseEntity<>(
                this.userService.selectPersonalSupport(),
                HttpStatus.OK
        );
    }


}
