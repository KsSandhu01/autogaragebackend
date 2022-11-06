package com.example.autogaragebackend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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
    private DataSource dataSource;


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        try {
            auth.inMemoryAuthentication()
                    .withUser("admin").password("{noop}password").roles("ADMINISTRATIEFMEDEWERKER")
                    .and()
                    .withUser("kassamedewerker").password("{noop}password").roles("KASSAMEDEWERKER")
                    .and()
                    .withUser("monteur").password("{noop}password").roles("MONTEUR");
        } catch (Exception e) {
            e.printStackTrace();
        }

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
                .antMatchers(HttpMethod.POST,"/v1/afspraak/**").hasRole("ADMINISTRATIEFMEDEWERKER")

                .anyRequest().authenticated()
                .and()
                .csrf().disable();


    }


}
