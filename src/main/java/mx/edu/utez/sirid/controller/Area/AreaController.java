package mx.edu.utez.sirid.controller.Area;


import mx.edu.utez.sirid.controller.Area.dtos.AreaDTO;
import mx.edu.utez.sirid.model.AcademicDivision.AcademicDivision;
import mx.edu.utez.sirid.model.Area.Area;
import mx.edu.utez.sirid.service.Area.AreaService;
import mx.edu.utez.sirid.utils.inserts.CustomResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api-sirid/area/")
@CrossOrigin(origins = {"*"})
public class AreaController {
    @Autowired
    private AreaService service;

    //trae todos
    @GetMapping("/")
    public  ResponseEntity<CustomResponse<List<Area>>> getAll(){
    return new ResponseEntity<>(this.service.getAll(),
            HttpStatus.OK);
    }

    //trae uno nuevo
    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<Area>> getOne(@PathVariable("id") Long id){
        return new ResponseEntity<>(
                this.service.getOne(id),
                HttpStatus.OK
        );
    }

    //inserta uno
    @PostMapping("/")
    public ResponseEntity<CustomResponse<Area>> insert(
            @RequestBody AreaDTO areaDTO, @Valid BindingResult result){
        if (result.hasErrors()){
            return new ResponseEntity<>(
                    null,
                    HttpStatus.BAD_REQUEST
            );
        }

        return  new ResponseEntity<>(
                this.service.insert(areaDTO.castToArea()),
                HttpStatus.CREATED
        );

    }

    //trae todos ordenados alfabeticamente
    @GetMapping("/select")
    public ResponseEntity<CustomResponse<List<Area>>> select() {
        return new ResponseEntity<>(
                this.service.selectAreas(),
                HttpStatus.OK
        );
    }


}
