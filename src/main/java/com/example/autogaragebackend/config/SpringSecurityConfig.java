package com.example.autogaragebackend.config;

import com.example.autogaragebackend.service.MedewerkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity

public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MedewerkerService medewerkerService;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/v1/medewerkers/**").hasRole("ADMINISTRATIEFMEDEWERKER")
                .antMatchers("/v1/autos/**").hasRole("ADMINISTRATIEFMEDEWERKER")
                .antMatchers("/v1/klanten/**").hasRole("ADMINISTRATIEFMEDEWERKER")
                .antMatchers("/v1/onderdelen/**").hasRole("ADMINISTRATIEFMEDEWERKER")
                .antMatchers("/v1/handelingen/**").hasRole("ADMINISTRATIEFMEDEWERKER")
                .antMatchers("/v1/afspraak/voegOverigeHandeling/**").hasRole("MONTEUR")
                .antMatchers("/v1/afspraak/updatestatus/**").hasRole("MONTEUR")
                .antMatchers("/v1/afspraak/voegOnderdeel/**").hasRole("MONTEUR")
                .antMatchers("/v1/afspraak/voegHandeling/**").hasRole("MONTEUR")
                .antMatchers("/v1/afspraak/voegOverigeHandeling/**").hasRole("MONTEUR")
                .antMatchers(HttpMethod.GET, "/v1/afspraak/**").hasRole("MONTEUR")
                .antMatchers(HttpMethod.GET, "/v1/bon/**").hasRole("KASSAMEDEWERKER")
                .antMatchers(HttpMethod.POST, "/v1/afspraak/**").hasRole("ADMINISTRATIEFMEDEWERKER")

                .anyRequest().authenticated()
                .and()
                .csrf().disable();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(medewerkerService);
        provider.setPasswordEncoder(new BCryptPasswordEncoder());
        return provider;
    }


}
