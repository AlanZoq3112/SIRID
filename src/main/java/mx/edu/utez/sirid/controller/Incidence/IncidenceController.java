package mx.edu.utez.sirid.controller.Incidence;

import mx.edu.utez.sirid.controller.Incidence.dtos.IncidenceDTO;
import mx.edu.utez.sirid.model.Incidence.Incidence;
import mx.edu.utez.sirid.service.Incidence.IncidenceService;
import mx.edu.utez.sirid.utils.inserts.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api-sirid/incidence")
@CrossOrigin(origins = {"*"})
public class IncidenceController {
    @Autowired
    private IncidenceService service;


    @GetMapping("/")
    public ResponseEntity<CustomResponse<List<Incidence>>> getAll(){
        return new ResponseEntity<>(
                this.service.getAll(),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<Incidence>> getOne(@PathVariable("id") Long id) {
        return new ResponseEntity<>(
                this.service.getOne(id),
                HttpStatus.OK
        );
    }

    @PostMapping("/")
    public ResponseEntity<CustomResponse<Incidence>> insert(
            @Valid @RequestBody IncidenceDTO incidence,
            @Valid BindingResult result
    ) {
        return new ResponseEntity<>(
                this.service.insert(incidence.castToIncidence()),
                HttpStatus.CREATED);
    }
}