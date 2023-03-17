package mx.edu.utez.sirid.utils;

import mx.edu.utez.sirid.enums.Areas;
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
        Area d1 = new Area(Areas.Docencia_1);
        Area d2 = new Area(Areas.Docencia_2);
        Area d3 = new Area(Areas.Docencia_3);
        Area d4 = new Area(Areas.Docencia_4);
        Area d5 = new Area(Areas.Docencia_5);
        Area cedim = new Area(Areas.CEDIM);
        Area ceviset = new Area(Areas.CEVISET);
        Area cecadec = new Area(Areas.CECADEC);
        Area rectoria = new Area(Areas.Rectoria);
        Area biblioteca = new Area(Areas.Biblioteca);
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
