package mx.edu.utez.sirid.security.model;

import lombok.Getter;
import lombok.Setter;
import mx.edu.utez.sirid.model.User.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class UserAuth implements UserDetails {
    private String email;
    private String password;
    private User user;

    private Collection<? extends GrantedAuthority> authorities;

    public UserAuth(String email, String password, User user,
                    Collection<? extends GrantedAuthority> authorities) {
        this.email = email;
        this.password = password;
        this.user = user;
        this.authorities = authorities;
    }


    public static UserAuth build(User user) {
        List<GrantedAuthority> authorities =
                user.getRoles().stream().
                        map(role -> new SimpleGrantedAuthority(role.getName().name()))
                        .collect(Collectors.toList());

        return new UserAuth(
                user.getCorreoElectronico(),
                user.getContraseña(),
                user,
                authorities
        );
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {

        return true;
    }

    @Override
    public boolean isAccountNonLocked() {

        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {

        return true;
    }

    @Override
    public boolean isEnabled() {

        return true;
    }



}
