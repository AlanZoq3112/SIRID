package mx.edu.utez.sirid.controller.Role;

import mx.edu.utez.sirid.controller.Role.dtos.RoleDTO;
import mx.edu.utez.sirid.model.Role.Role;
import mx.edu.utez.sirid.service.Role.RoleService;
import mx.edu.utez.sirid.utils.inserts.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api-sirid/role/")
@CrossOrigin(origins = {"*"})
public class RoleController {
    @Autowired
    private RoleService service;

    //trae todos
    @GetMapping("/")
    public ResponseEntity<CustomResponse<List<Role>>> getAll() {
        return new ResponseEntity<>(
                this.service.getAll(),
                HttpStatus.OK
        );
    }

    //trae todos ordenados alfabeticamente
    @GetMapping("/select")
    public ResponseEntity<CustomResponse<List<Role>>> select() {
        return new ResponseEntity<>(
                this.service.selectRole(),
                HttpStatus.OK
        );
    }

    //trae solo uno
    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<Role>> getOne(@PathVariable("id") Long id){
        return new ResponseEntity<>(
                this.service.getOne(id),
                HttpStatus.OK
        );
    }

    //guarda uno nuevo
    @PostMapping("/")
    public ResponseEntity<CustomResponse<Role>> insert(
            @RequestBody RoleDTO roleDto, @Valid BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(
                    null,
                    HttpStatus.BAD_REQUEST
            );
        }
        return new ResponseEntity<>(
                this.service.insert(roleDto.getRole()),
                HttpStatus.CREATED
        );
    }
}
