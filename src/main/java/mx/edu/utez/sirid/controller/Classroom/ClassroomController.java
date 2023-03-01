package mx.edu.utez.sirid.controller.Classroom;


import mx.edu.utez.sirid.controller.Classroom.dtos.ClassroomDTO;
import mx.edu.utez.sirid.model.Classroom.Classroom;
import mx.edu.utez.sirid.model.Role.Role;
import mx.edu.utez.sirid.service.Classroom.ClassroomService;
import mx.edu.utez.sirid.utils.CustomResponse;
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

    @GetMapping("/")
    public ResponseEntity<CustomResponse<List<Classroom>>> getAll() {
        return new ResponseEntity<>(
                this.service.getAll(),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<Classroom>> getOne(@PathVariable("id") Long id) {
        return new ResponseEntity<>(
                this.service.getOne(id),
                HttpStatus.OK
        );
    }

    @PostMapping("/")
    public ResponseEntity<CustomResponse<Classroom>> insert(
            @Valid @RequestBody ClassroomDTO category,
            @Valid BindingResult result
    ) {
        return new ResponseEntity<>(
                this.service.insert(category.castToClassroom()),
                HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<CustomResponse<Classroom>> update(
            @Valid @RequestBody ClassroomDTO classroom,
            BindingResult result
    ) {
        return new ResponseEntity<>(
                this.service.update(classroom.castToClassroom()),
                HttpStatus.CREATED);
    }

    @PatchMapping("/")
    public ResponseEntity<CustomResponse<Boolean>> enableOrDisable(
            @RequestBody ClassroomDTO classroom
    ) {
        return new ResponseEntity<>(
                this.service.changeStatus(classroom.castToClassroom()),
                HttpStatus.OK
        );
    }

}
