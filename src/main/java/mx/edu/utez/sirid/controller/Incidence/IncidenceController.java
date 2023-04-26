package mx.edu.utez.sirid.controller.Incidence;

import mx.edu.utez.sirid.controller.Incidence.dtos.IncidenceDTO;
import mx.edu.utez.sirid.controller.Status.dtos.StatusDTO;
import mx.edu.utez.sirid.controller.User.dtos.UserDTO;
import mx.edu.utez.sirid.model.Incidence.Incidence;
import mx.edu.utez.sirid.service.Incidence.IncidenceService;
import mx.edu.utez.sirid.utils.inserts.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api-sirid/incidence")
@CrossOrigin(origins = {"*"})
public class IncidenceController {
    @Autowired
    private IncidenceService service;

    //recuperar todas las incidencias
    @GetMapping("/")
    public ResponseEntity<CustomResponse<List<Incidence>>> getAll(){
        return new ResponseEntity<>(
                this.service.getAll(),
                HttpStatus.OK
        );
    }

    //trae las imagenes de la incidencia
    @GetMapping("/loadfile/{uid}")
    public ResponseEntity<Resource> loadfile(@PathVariable("uid") String uid) throws IOException {
        return this.service.getImage(uid);
    }

    //recuperar una incidencia en especifico
    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<Incidence>> getOne(@PathVariable("id") Long id) {
        return new ResponseEntity<>(
                this.service.getOne(id),
                HttpStatus.OK
        );
    }

    //registrar una nueva incidecia
    @PostMapping("/")
    public ResponseEntity<CustomResponse<Incidence>> insert(@Valid @RequestBody IncidenceDTO incidence, BindingResult result
    ) {if (result.hasErrors()) {
        return new ResponseEntity<>(
                null,
                HttpStatus.BAD_REQUEST
        );
    }
        return new ResponseEntity<>(
                this.service.insert(incidence.castToIncidence()),
                HttpStatus.CREATED);
    }

    //Actualizar una incidencia
    @PutMapping("/")
    public  ResponseEntity<CustomResponse<Incidence>> update(@RequestBody IncidenceDTO incidenceDTO){
        return new ResponseEntity<>(
                this.service.update(incidenceDTO.castToIncidence()),
                HttpStatus.ACCEPTED
        );
    }

    //Cambiar status de la incidencia
    @PatchMapping("/")
    public  ResponseEntity<CustomResponse<Integer>> changeStatus( @RequestBody IncidenceDTO incidenceDTO){
        return new ResponseEntity<>(
                this.service.changeStatus(incidenceDTO.finalizeIncident()),
                HttpStatus.ACCEPTED
        );
    }

    //Cambio del Personal de Soprte
    @PatchMapping("/changeSupport")
    public  ResponseEntity<CustomResponse<Integer>> changePersonalsupport(@Valid @RequestBody IncidenceDTO incidenceDTO,BindingResult result) throws MessagingException {
        if (result.hasErrors())
            return  new ResponseEntity<>(
                    null,HttpStatus.BAD_REQUEST
            );

            return  new ResponseEntity<>(
                    this.service.changePersonalSupport(incidenceDTO.changePersonalSuport()),HttpStatus.OK
            );

    }

    //finaliza la incidencia
    @PatchMapping("/finalizeIncident")
    public ResponseEntity<CustomResponse<Integer>> finalizeIncident(@Valid @RequestBody IncidenceDTO incidenceDTO,BindingResult result) throws MessagingException {
        if (result.hasErrors())return   new ResponseEntity<>(
                null,HttpStatus.BAD_REQUEST
        );
        return  new ResponseEntity<>(
                this.service.finalizeIncident(incidenceDTO.finalizeIncident()), HttpStatus.ACCEPTED
        );
    }

    //ver las incidencias en las que participa el personal de soporte de acuerdo a su status
    @GetMapping("/lookIncidenceSupport")
    public  ResponseEntity<CustomResponse<List<Incidence>>> lookIncidenceSupport(@RequestBody IncidenceDTO incidenceDTO){
        return  new ResponseEntity<>(
                this.service.lookIncidenceSupport(incidenceDTO.getStatus(),incidenceDTO.getPersonalSoporte() ),
                HttpStatus.OK
        );
    }

    //ver las incidencias en las que participa el docente de acuerdo a su status
    @GetMapping("/lookIncidenceTeacher")
    public  ResponseEntity<CustomResponse<List<Incidence>>> lookIncidenceTeacher(@RequestBody IncidenceDTO incidenceDTO){
        return  new ResponseEntity<>(
                this.service.lookIncidenceDocente(incidenceDTO.getStatus(),incidenceDTO.getDocente()),
                HttpStatus.OK
        );
    }

    @PostMapping("/messageTeacher")
    public  ResponseEntity<String> MessageTeacher(@RequestBody IncidenceDTO incidenceDTO) throws MessagingException {
        return  new ResponseEntity<>(
                this.service.newMessageTeacher(incidenceDTO.castToIncidence()),
                HttpStatus.OK
        );
    }

    @PostMapping("/messageSupport")
    public  ResponseEntity<String> Messagesupport(@RequestBody IncidenceDTO incidenceDTO) throws MessagingException {
        return  new ResponseEntity<>(
                this.service.newMessageSupport(incidenceDTO.castToIncidence()),
                HttpStatus.OK
        );
    }










}