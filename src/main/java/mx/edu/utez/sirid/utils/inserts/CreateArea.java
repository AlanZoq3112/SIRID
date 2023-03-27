package mx.edu.utez.sirid.utils.inserts;

import mx.edu.utez.sirid.model.Area.Area;
import mx.edu.utez.sirid.service.Area.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CreateArea implements CommandLineRunner {

    @Autowired
    AreaService areaService;

    @Override
    public void run(String... args) throws Exception {
        Area d1 = new Area("Docencia 1");
        Area d2 = new Area("Docencia 2");
        Area d3 = new Area("Docencia 3");
        Area d4 = new Area("Docencia 4");
        Area d5 = new Area("Docencia 5");
        Area cedim = new Area("CEDIM");
        Area ceviset = new Area("CEVISET");
        Area cecadec = new Area("CECADEC");
        Area rectoria = new Area("Rectoria");
        Area biblioteca = new Area("Biblioteca");
        areaService.insert(d1);
        areaService.insert(d2);
        areaService.insert(d3);
        areaService.insert(d4);
        areaService.insert(d5);
        areaService.insert(cedim);
        areaService.insert(cecadec);
        areaService.insert(ceviset);
        areaService.insert(rectoria);
        areaService.insert(biblioteca);
        System.out.println("CreateAreas -> Areas Complete!!");
    }
}
