package mx.edu.utez.sirid.service.Status;
import mx.edu.utez.sirid.model.Status.Status;
import mx.edu.utez.sirid.model.Status.IStatusRepository;
import mx.edu.utez.sirid.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.sql.SQLException;
import java.util.List;

@Service
@Transactional
public class StatusService {
    @Autowired
    private IStatusRepository repository;

    @Transactional(readOnly = true)
    public CustomResponse<List<Status>> getAll() {
        return new CustomResponse<>(
                this.repository.findAll(),
                false,
                200,
                "Ok"
        );
    }

    @Transactional(readOnly = true)
    public CustomResponse<Status> getOne(Long id) {
        return new CustomResponse<>(
                this.repository.findById(id).get(),
                false,
                200,
                "Okey"
        );
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Status> insert(Status status) {
        if (this.repository.existsByName(status.getName()))
            return new CustomResponse<>(
                    null,
                    true,
                    400,
                    "Status registradoo"
            );
        return new CustomResponse<>(
                this.repository.saveAndFlush(status),
                false,
                200,
                "Status registered succesfully"
        );
    }
}