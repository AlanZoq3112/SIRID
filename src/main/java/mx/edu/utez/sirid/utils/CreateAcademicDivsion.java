package mx.edu.utez.sirid.utils;

import mx.edu.utez.sirid.enums.DivisionesAcademicas;
import mx.edu.utez.sirid.model.AcademicDivision.AcademicDivision;
import mx.edu.utez.sirid.service.AcademicDivision.AcademicDivisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CreateAcademicDivsion implements CommandLineRunner {

    @Autowired
    AcademicDivisionService academicDivisionService;


    @Override
    public void run(String... args) throws Exception {

        System.out.println("CreateAcademicDivision -> Divisions complete!!");
        AcademicDivision datid = new AcademicDivision(DivisionesAcademicas.DATID);
        AcademicDivision dacea = new AcademicDivision(DivisionesAcademicas.DACEA);
        AcademicDivision dami = new AcademicDivision(DivisionesAcademicas.DAMI);
        AcademicDivision datefi = new AcademicDivision(DivisionesAcademicas.DATEFI);


        academicDivisionService.insert(datid);
        academicDivisionService.insert(dacea);
        academicDivisionService.insert(dami);
        academicDivisionService.insert(datefi);

    }
}
