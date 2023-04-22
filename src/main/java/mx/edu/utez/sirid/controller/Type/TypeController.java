package mx.edu.utez.sirid.controller.Type;


import mx.edu.utez.sirid.controller.Type.dtos.TypeDto;
import mx.edu.utez.sirid.model.Type.Type;
import mx.edu.utez.sirid.service.Type.TypeService;
import mx.edu.utez.sirid.utils.inserts.CustomResponse;
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

    //trae todos
    @GetMapping("/")
    public  ResponseEntity<CustomResponse<List<Type>>> getAll(){
        return new ResponseEntity<>(
                this.service.getAll(), HttpStatus.OK);
    }

    //trae uno por su id
    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<Type>> getOne(@PathVariable("id") Long id){
        return new ResponseEntity<>(
                this.service.getOne(id),HttpStatus.OK
        );
    }

    //inserta uno nuevo
    @PostMapping("/")
    public ResponseEntity<CustomResponse<Type>> insert(
            @RequestBody TypeDto typeDto, @Valid BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(
                    null,
                    HttpStatus.BAD_REQUEST
            );
        }
        return new ResponseEntity<>(
                this.service.insert(typeDto.getType()),
                HttpStatus.CREATED
        );
    }

    //trae todos ordenados alfabeticamente
    @GetMapping("/select")
    public  ResponseEntity<CustomResponse<List<Type>>> select(){
        return new ResponseEntity<>(
                this.service.selectType(),
                HttpStatus.OK);
    }

}
