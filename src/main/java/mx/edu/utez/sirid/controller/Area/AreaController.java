package mx.edu.utez.sirid.controller.Area;


import mx.edu.utez.sirid.model.Area.Area;
import mx.edu.utez.sirid.service.Area.AreaService;
import mx.edu.utez.sirid.utils.CustomResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api-sirid/area")
@CrossOrigin(origins = {"*"})
public class AreaController {
    @Autowired
    private AreaService service;

    @GetMapping("/")
    public  ResponseEntity<CustomResponse<List<Area>>> getAll(){
    return new ResponseEntity<>(this.service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<Area>> getOne(@PathVariable("id") Long id){
        return new ResponseEntity<>(
                this.service.getOne(id),HttpStatus.OK
        );
    }
}
