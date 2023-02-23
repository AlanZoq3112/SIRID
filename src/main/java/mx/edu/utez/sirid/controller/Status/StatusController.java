package mx.edu.utez.sirid.controller.Status;
import mx.edu.utez.sirid.controller.Status.dtos.StatusDTO;
import mx.edu.utez.sirid.model.Status.Status;
import mx.edu.utez.sirid.service.Status.StatusService;
import mx.edu.utez.sirid.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api-sirid/status/")
@CrossOrigin(origins = {"*"})
public class StatusController {
    @Autowired
    private StatusService service;

    @GetMapping("/")
    public ResponseEntity<CustomResponse<List<Status>>> getAll() {
        return new ResponseEntity<>(
                this.service.getAll(),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<Status>> getOne(@PathVariable("id") Long id) {
        return new ResponseEntity<>(
                this.service.getOne(id),
                HttpStatus.OK
        );
    }

    @PostMapping("/")
    public ResponseEntity<CustomResponse<Status>> insert(
            @RequestBody StatusDTO statusDTO, @Valid BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(
                    null,
                    HttpStatus.BAD_REQUEST
            );
        }
        return new ResponseEntity<>(
                this.service.insert(statusDTO.getStatus()),
                HttpStatus.CREATED
        );
    }


}