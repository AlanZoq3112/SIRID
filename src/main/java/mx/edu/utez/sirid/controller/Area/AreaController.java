package mx.edu.utez.sirid.controller.Area;


<<<<<<< HEAD
import mx.edu.utez.sirid.controller.Area.dtos.AreaDTO;
import mx.edu.utez.sirid.controller.Type.dtos.TypeDTO;
=======
import mx.edu.utez.sirid.controller.AcademicDivision.dtos.AcademicDivisionDTO;
import mx.edu.utez.sirid.controller.Area.dtos.AreaDTO;
>>>>>>> bda133956100d56c6e15099595aa72cdf3599592
import mx.edu.utez.sirid.model.Area.Area;
import mx.edu.utez.sirid.model.Type.Type;
import mx.edu.utez.sirid.service.Area.AreaService;
import mx.edu.utez.sirid.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
<<<<<<< HEAD

=======
>>>>>>> bda133956100d56c6e15099595aa72cdf3599592
import java.util.List;

@RestController
@RequestMapping("/api-sirid/area/")
@CrossOrigin(origins = {"*"})
public class AreaController {
    @Autowired
    private AreaService service;

    @GetMapping("/")
<<<<<<< HEAD
    public ResponseEntity<CustomResponse<List<Area>>> getAll() {
        return new ResponseEntity<>(
                this.service.getAll(),
                HttpStatus.OK
        );
=======
    public  ResponseEntity<CustomResponse<List<Area>>> getAll(){
    return new ResponseEntity<>(this.service.getAll(),
            HttpStatus.OK);
>>>>>>> bda133956100d56c6e15099595aa72cdf3599592
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<Area>> getOne(@PathVariable("id") Long id) {
        return new ResponseEntity<>(
                this.service.getOne(id),
                HttpStatus.OK
        );
    }

    @PostMapping("/")
    public ResponseEntity<CustomResponse<Area>> insert(
<<<<<<< HEAD
            @Valid @RequestBody AreaDTO area,
            @Valid BindingResult result
    ) {
        return new ResponseEntity<>(
                this.service.insert(area.getArea()),
                HttpStatus.CREATED);
    }
=======
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


>>>>>>> bda133956100d56c6e15099595aa72cdf3599592
}
