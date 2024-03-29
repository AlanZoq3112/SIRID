package mx.edu.utez.sirid.utils.inserts;


import mx.edu.utez.sirid.model.Role.Role;
import mx.edu.utez.sirid.service.Role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CreateRoles implements CommandLineRunner {

    @Autowired
    RoleService roleService;

    @Override
    public void run(String... args) throws Exception {
        Role superAdmin= new Role(null,"SuperAdmin",null);
        Role soporteTecnico = new Role(null,"Soporte Tecnico",null);
        Role docente= new Role (null,"Docente",null);
        roleService.insert(superAdmin);
        roleService.insert(soporteTecnico);
        roleService.insert(docente);
        System.out.println("CreateRoles -> roles complete!!");
    }
}
