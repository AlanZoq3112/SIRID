package mx.edu.utez.sirid.enums;

import mx.edu.utez.sirid.model.Role.Role;

public enum AdminRoot{

    SUPERADMIN(
            1,
            "Root",
            "Admin",
            "Admin",
            "20213tn014@utez.edu.mx",
            "20213tn014sa9*",
            "7771234565",
            true,
            null,
            "SuperAdmin"
    )

    private int id;
    private String name;
    private String firstName;.
    private String lastName;
    private String email;
    private String password;
    private String celphone;
    private boolean status;
    private boolean changeStatus;
    private Role role;

    AdminRoot(Long id,
              String name,
              String firstName,
              String lastName,
              String email,
              String password,
              String celphone,
              Boolean status,
              Boolean changeStatus,
              Role role
    )
    {

        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.password = password;
        this.celphone = celphone;
        this.status = status;
        this.changeStatus = changeStatus;
        this.role = role;
    }
}
