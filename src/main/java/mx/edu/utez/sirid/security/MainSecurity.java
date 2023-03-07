package mx.edu.utez.sirid.security;

import mx.edu.utez.sirid.security.jwt.JwtEntryPoint;
import mx.edu.utez.sirid.security.jwt.JwtProvider;
import mx.edu.utez.sirid.security.jwt.JwtTokenFilter;
import mx.edu.utez.sirid.security.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MainSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtEntryPoint entryPoint;

    @Bean
    public JwtTokenFilter jwtTokenFilter(){
        return new JwtTokenFilter();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    public  void  configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(authService).passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws  Exception{
        return  super.authenticationManagerBean();
    }

    @Override
    public AuthenticationManager authenticationManager() throws Exception{
        return super.authenticationManager();
    }

    @Override
    protected void  configure(HttpSecurity httpSecurity) throws  Exception{
        /*httpSecurity.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers("/api-sirid/auth/**").permitAll()
                .antMatchers("/api-sirid/academic/**").permitAll()
                .antMatchers("/sirid-api/classroom/**").permitAll()
                .antMatchers("/sirid-api/status/**").permitAll()
                .antMatchers("/sirid-api/user/**").permitAll()
                .antMatchers("/sirid-api/type/**").permitAll()
                .antMatchers("/sirid-api/role/**").permitAll()
                .antMatchers("/sirid-area/**").permitAll().anyRequest().authenticated()
                .and().exceptionHandling().authenticationEntryPoint(entryPoint)
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
            httpSecurity.addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);*/
    }

}
