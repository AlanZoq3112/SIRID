package mx.edu.utez.sirid.utils.inserts;

import mx.edu.utez.sirid.enums.EStatus;
import mx.edu.utez.sirid.model.Status.Status;
import mx.edu.utez.sirid.service.Status.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CreateStatus implements CommandLineRunner {

    @Autowired
    StatusService statusService;


    @Override
    public void run(String... args) throws Exception {
        Status pendiente = new Status( EStatus.Pendiente);
        Status activo = new Status(EStatus.Activo);
        Status concludo = new Status(EStatus.Concluido);
        statusService.insert(pendiente);
        statusService.insert(activo);
        statusService.insert(concludo);
        System.out.println("CreateStatus-> status complete!!");
    }
}
