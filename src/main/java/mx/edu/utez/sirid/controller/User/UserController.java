package mx.edu.utez.sirid.controller.User;
import mx.edu.utez.sirid.controller.User.dtos.UserDTO;
import mx.edu.utez.sirid.model.User.User;
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
    private UserService service;
    @GetMapping("/")
    public ResponseEntity<CustomResponse<List<User>>> getAll(){
        return new ResponseEntity<>(
                this.service.getALll(),
                HttpStatus.OK
        );
    }
    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<User>> getOne(@PathVariable("id") Long id){
        return new ResponseEntity<>(
                this.service.getOne(id),
                HttpStatus.OK
        );
    }

    @PostMapping("/")
    public ResponseEntity<CustomResponse<User>> insert(
            @RequestBody UserDTO userDTO, @Valid BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(
                    null,
                    HttpStatus.BAD_REQUEST
            );
        }
        return new ResponseEntity<>(
                this.service.insert(userDTO.getUser()),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/")
    public ResponseEntity<CustomResponse<User>> update(
            @Valid @RequestBody UserDTO userDTO,
            BindingResult result
    ) {
        return new ResponseEntity<>(
                this.service.update(userDTO.getUser()),
                HttpStatus.OK);
    }

    @PatchMapping("/")
    public ResponseEntity<CustomResponse<Boolean>> changeStatus(
            @RequestBody UserDTO userDTO
    ) {
        return new ResponseEntity<>(
                this.service.changeStatus(userDTO.getUser()),
                HttpStatus.OK
        );
    }

    @PutMapping("/recoverPassword")
    public ResponseEntity<CustomResponse<Integer>> recoverpasword(
            @Valid @RequestBody UserDTO userDTO,
            BindingResult result
    ) throws MessagingException {
        return new ResponseEntity<>(
                this.service.recoverPassword(userDTO.getUser()),
                HttpStatus.OK);
    }


    @PutMapping("/changePassword")
    public ResponseEntity<CustomResponse<Integer>> changePassword(
            @RequestBody UserDTO userDTO, @Valid BindingResult result) throws MessagingException {
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
                this.service.changePassword(userDTO.getUser()),
                HttpStatus.OK
        );
    }
}
