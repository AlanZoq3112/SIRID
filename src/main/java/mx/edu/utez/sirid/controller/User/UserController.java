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

    @GetMapping("/")
    public ResponseEntity<CustomResponse<List<User>>> getAll(RoleDTO roleDTO){
        return new ResponseEntity<>(
                this.userService.getALll(roleDTO.getRole()),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<User>> getOne(@PathVariable("id") String email){
        return new ResponseEntity<>(
                this.userService.getOne(email),
                HttpStatus.OK
        );
    }

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

    @PutMapping("/")
    public ResponseEntity<CustomResponse<User>> update( @Valid @RequestBody UserDTO userDTO, BindingResult result
    ) {
        return new ResponseEntity<>(
                this.userService.update(userDTO.getUser()),
                HttpStatus.OK);
    }

    @PatchMapping("/")
    public ResponseEntity<CustomResponse<Boolean>> changeStatus( @RequestBody UserDTO userDTO
    ) {
        return new ResponseEntity<>(
                this.userService.changeStatus(userDTO.getUser()),
                HttpStatus.OK
        );
    }

    @PatchMapping("/recoverPassword")
    public ResponseEntity<CustomResponse<Integer>> recoverpasword( @Valid @RequestBody UserDTO userDTO, BindingResult result) throws MessagingException
    {
        return new ResponseEntity<>(
                this.userService.recoverPassword(userDTO.getUser()),
                HttpStatus.OK);
    }


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
}
