package mx.edu.utez.sirid.controller.Type;


<<<<<<< HEAD
import mx.edu.utez.sirid.controller.Type.dtos.TypeDTO;
=======
>>>>>>> bda133956100d56c6e15099595aa72cdf3599592
import mx.edu.utez.sirid.model.Type.Type;
import mx.edu.utez.sirid.service.Type.TypeService;
import mx.edu.utez.sirid.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
<<<<<<< HEAD
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

=======
import org.springframework.web.bind.annotation.*;


import java.util.List;


>>>>>>> bda133956100d56c6e15099595aa72cdf3599592
@RestController
@RequestMapping("/api-sirid/type")
@CrossOrigin(origins = {"*"})
public class TypeController {
<<<<<<< HEAD
=======

>>>>>>> bda133956100d56c6e15099595aa72cdf3599592
    @Autowired
    private TypeService service;

    @GetMapping("/")
<<<<<<< HEAD
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
=======
    public  ResponseEntity<CustomResponse<List<mx.edu.utez.sirid.model.Type.Type>>> getAll(){
        return new ResponseEntity<>(
                this.service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<Type>> getOne(@PathVariable("id") Long id){
        return new ResponseEntity<>(
                this.service.getOne(id),HttpStatus.OK
        );
    }
>>>>>>> bda133956100d56c6e15099595aa72cdf3599592
}
