package mx.edu.utez.sirid.utils;

import mx.edu.utez.sirid.model.User.User;
import mx.edu.utez.sirid.service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

public class CreateSuperAdmin implements CommandLineRunner {

    @Autowired
    UserService userService;

    @Override
    public void run(String... args) throws Exception {
        User root = new User()
    }
}
