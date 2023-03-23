package mx.edu.utez.sirid.utils.inserts;

import mx.edu.utez.sirid.enums.TiposDeSalones;
import mx.edu.utez.sirid.model.Type.Type;
import mx.edu.utez.sirid.service.Type.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CreateType  implements CommandLineRunner {

    @Autowired
    TypeService typeService;

    @Override
    public void run(String... args) throws Exception {
        Type centroComputo= new Type(null,TiposDeSalones.CentroDeComputo,null);
        Type compuAula = new Type(null,TiposDeSalones.CompuAula,null);
        Type aula = new Type(null,TiposDeSalones.Aula,null);
        Type oficina = new Type(null,TiposDeSalones.Oficina,null);
        Type auditorio = new Type(null,TiposDeSalones.Auditorio,null);

        typeService.insert(centroComputo);
        typeService.insert(compuAula);
        typeService.insert(aula);
        typeService.insert(oficina);
        typeService.insert(auditorio);
        System.out.println("CreateType -> types complete!!");
    }
}
