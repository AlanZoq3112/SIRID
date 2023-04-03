package mx.edu.utez.sirid.utils.inserts;


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


        AcademicDivision datid = new AcademicDivision("DATID");
        AcademicDivision dacea = new AcademicDivision("DACEA");
        AcademicDivision dami = new AcademicDivision("DAMI");
        AcademicDivision datefi = new AcademicDivision("DATEFI");
        AcademicDivision NA = new AcademicDivision("No pertenece a ninguna division ");


        academicDivisionService.insert(datid);
        academicDivisionService.insert(dacea);
        academicDivisionService.insert(dami);
        academicDivisionService.insert(datefi);
        academicDivisionService.insert(NA);

        System.out.println("CreateAcademicDivision -> Divisions complete!!");

    }
}
