package mx.edu.utez.sirid.controller.Type;


import mx.edu.utez.sirid.controller.Type.dtos.TypeDTO;
import mx.edu.utez.sirid.model.Type.Type;
import mx.edu.utez.sirid.service.Type.TypeService;
import mx.edu.utez.sirid.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api-sirid/type")
@CrossOrigin(origins = {"*"})
public class TypeController {
    @Autowired
    private TypeService service;

    @GetMapping("/")
    public ResponseEntity<CustomResponse<List<Type>>> getAll() {
        return new ResponseEntity<>(
                this.service.getAll(),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<Type>> getOne(@PathVariable("id") Long id) {
        return new ResponseEntity<>(
                this.service.getOne(id),
                HttpStatus.OK
        );
    }

    @PostMapping("/")
    public ResponseEntity<CustomResponse<Type>> insert(
            @Valid @RequestBody TypeDTO type,
            @Valid BindingResult result
    ) {
        return new ResponseEntity<>(
                this.service.insert(type.getType()),
                HttpStatus.CREATED);
    }
}
