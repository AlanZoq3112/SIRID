package mx.edu.utez.sirid.security.controller;


import mx.edu.utez.sirid.security.controller.dto.LoginDTO;
import mx.edu.utez.sirid.security.jwt.JwtProvider;
import mx.edu.utez.sirid.utils.inserts.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api-sirid/auth")
@CrossOrigin(origins = {"*"})
public class AuthController {
    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private JwtProvider provider;

    @PostMapping("login")
    public ResponseEntity<CustomResponse<Map<String, Object>>> login(
            @Valid @RequestBody LoginDTO loginDTO) {
        Authentication authentication = manager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDTO.getEmail(),
                        loginDTO.getPassword()
                )
        );
        UserDetails userDetails=(UserDetails) authentication.getPrincipal();
        System.out.println("AuthController ->"+userDetails.isEnabled());
       /* if (!userDetails.isEnabled()){
            return new ResponseEntity<>(
                    new CustomResponse<>(null, true, 403, "Usuario bloqueado"),
                    HttpStatus.OK
            );
        }*/
        Map<String, Object> data= new HashMap<>();
        String token = provider.generaToken(authentication);
        data.put("token", token);
        data.put("user", userDetails);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new ResponseEntity<>(
                new CustomResponse<>(data, false, 200, "OK"),
                HttpStatus.OK
        );
    }
}
