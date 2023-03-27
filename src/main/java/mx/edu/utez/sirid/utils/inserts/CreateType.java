package mx.edu.utez.sirid.utils.inserts;


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
        Type centroComputo= new Type(null,"CentroDeComputo",null);
        Type compuAula = new Type(null,"CompuAula",null);
        Type aula = new Type(null,"Aula",null);
        Type oficina = new Type(null,"Oficina",null);
        Type auditorio = new Type(null,"Auditorio",null);

        typeService.insert(centroComputo);
        typeService.insert(compuAula);
        typeService.insert(aula);
        typeService.insert(oficina);
        typeService.insert(auditorio);
        System.out.println("CreateType -> types complete!!");
    }
}
