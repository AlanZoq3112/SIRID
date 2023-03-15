package mx.edu.utez.sirid.service.AcademicDivision;

import mx.edu.utez.sirid.model.AcademicDivision.AcademicDivision;
import mx.edu.utez.sirid.model.AcademicDivision.IacademicDivisionRepository;
import mx.edu.utez.sirid.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
@Transactional
public class AcademicDivisionService {
    @Autowired
    private IacademicDivisionRepository repository;

    @Transactional(readOnly = true)
    public CustomResponse<List<AcademicDivision>> getAll() {
        return new CustomResponse<>(
                this.repository.findAll(),
                false,
                200,
                "Okey"
        );
    }

    @Transactional(readOnly = true)
    public CustomResponse<AcademicDivision> getOne(Long id) {
        return new CustomResponse<>(
                this.repository.findById(id).get(),
                false,
                200,
                "Okey"
        );
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<AcademicDivision> insert(AcademicDivision academicDivision) {
        if (this.repository.existsByName(academicDivision.getName()))
            return new CustomResponse<>(
                    null,
                    true,
                    400,
                    "Academic division has already been registered"
            );
        return new CustomResponse<>(
                this.repository.saveAndFlush(academicDivision),
                false,
                200,
                "Academic Division registered succesfully"
        );
    }
}
