package mx.edu.utez.sirid.controller.AcademicDivision;


import mx.edu.utez.sirid.controller.AcademicDivision.dtos.AcademicDivisionDTO;
import mx.edu.utez.sirid.model.AcademicDivision.AcademicDivision;
import mx.edu.utez.sirid.service.AcademicDivision.AcademicDivisionService;
import mx.edu.utez.sirid.utils.inserts.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api-sirid/academic/")
@CrossOrigin(origins = {"*"})
public class AcademicDivisionController {
    @Autowired
    private AcademicDivisionService service;

    @GetMapping("/")
    public ResponseEntity<CustomResponse<List<AcademicDivision>>> getAll() {
        return new ResponseEntity<>(
                this.service.getAll(),
                HttpStatus.OK
        );
    }

    @GetMapping("/select")
    public ResponseEntity<CustomResponse<List<AcademicDivision>>> selectAcademics() {
        return new ResponseEntity<>(
                this.service.selectAcademic(),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<AcademicDivision>> getOne(@PathVariable("id") Long id) {
        return new ResponseEntity<>(
                this.service.getOne(id),
                HttpStatus.OK
        );
    }

    @PostMapping("/")
    public ResponseEntity<CustomResponse<AcademicDivision>> insert(
            @RequestBody AcademicDivisionDTO academicDivisionDTO, @Valid BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(
                    null,
                    HttpStatus.BAD_REQUEST
            );
        }
        return new ResponseEntity<>(
                this.service.insert(academicDivisionDTO.getAcademicDivision()),
                HttpStatus.CREATED
        );
    }


}
