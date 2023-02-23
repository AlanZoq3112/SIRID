package mx.edu.utez.sirid.service.Area;

import mx.edu.utez.sirid.model.Area.Area;
import mx.edu.utez.sirid.model.Area.IAreaRepository;
import mx.edu.utez.sirid.utils.CustomResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
@Transactional
public class AreaService {
    @Autowired
    private IAreaRepository repository;

    @Transactional(readOnly = true)
    public CustomResponse<List<Area>> getAll(){
        return new CustomResponse<>(
                this.repository.findAll(),
                false,200,"OK"
        );
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Area> getOne(Long id){
        return new CustomResponse<>(
                this.repository.findById(id).get(),
                false,200,"OK"
        );
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Area> insert(Area area){
        if (!this.repository.existsById(area.getId())){
            return new CustomResponse<>(
                    null,true,400,"Esta area ya esta registrada"
            );
        }

        return new CustomResponse<>(
                this.repository.saveAndFlush(area),
                false,
                200,
                "Area registrada con exito"

        );
    }




}
