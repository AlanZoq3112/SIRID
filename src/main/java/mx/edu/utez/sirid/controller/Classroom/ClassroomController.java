package mx.edu.utez.sirid.controller.Classroom;

import mx.edu.utez.sirid.controller.Classroom.dtos.ClassroomDTO;
import mx.edu.utez.sirid.model.Classroom.Classroom;
import mx.edu.utez.sirid.service.Classroom.ClassroomService;
import mx.edu.utez.sirid.utils.inserts.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api-sirid/classroom")
@CrossOrigin(origins = {"*"})
public class ClassroomController {
    @Autowired
    private ClassroomService service;

    //trae todos
    @GetMapping("/")
    public ResponseEntity<CustomResponse<List<Classroom>>> getAll() {
        return new ResponseEntity<>(
                this.service.getAll(),
                HttpStatus.OK
        );
    }

    //trae todos los saolnes en orden edificio -> salon, activos ordenados alfabeticamente
    @GetMapping("/select")
    public ResponseEntity<CustomResponse<List<Classroom>>> select() {
        return new ResponseEntity<>(
                this.service.selectClassroms(),
                HttpStatus.OK
        );
    }

    //trae uno por su id
    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<Classroom>> getOne(@PathVariable("id") Long id) {
        return new ResponseEntity<>(
                this.service.getOne(id),
                HttpStatus.OK
        );
    }

    //guarda uno nuevo
    @PostMapping("/")
    public ResponseEntity<CustomResponse<Classroom>> insert(@Valid @RequestBody ClassroomDTO category, @Valid BindingResult result
    ) {
        return new ResponseEntity<>(
                this.service.insert(category.castToClassroom()),
                HttpStatus.CREATED);
    }

    //actualiza uno de acuerdo a su nombre
    @PutMapping("/")
    public ResponseEntity<CustomResponse<Classroom>> update(@RequestBody ClassroomDTO classroomDTO, @Valid BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(
                    null,
                    HttpStatus.BAD_REQUEST
            );
        }
        return new ResponseEntity<>(
                this.service.update(classroomDTO.castToClassroom()),
                HttpStatus.CREATED
        );
    }

    //Cambia su estatus (Activo o inactivi)
    @PatchMapping("/")
    public ResponseEntity<CustomResponse<Integer>> enableOrDisable(
            @RequestBody ClassroomDTO classroom
    ) {
        return new ResponseEntity<>(
                this.service.changeStatus(classroom.changeStatus()),
                HttpStatus.OK
        );
    }

    @GetMapping("/count/{id}")
    public ResponseEntity<CustomResponse<Long>> countIncidences(@PathVariable("id") Long id) {
        return new ResponseEntity<>(
                this.service.countIncidences(id),
                HttpStatus.OK
        );
    }
}