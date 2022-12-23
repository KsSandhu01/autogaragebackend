package com.example.autogaragebackend.config;

import com.example.autogaragebackend.service.MedewerkerService;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private  RsaKeyProperties jwtConfigProperties;
    @Autowired
    private MedewerkerService medewerkerService;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests(a->
                    a.antMatchers("/v1/auth/*").permitAll()
                    .antMatchers("/v1/medewerkers/*").permitAll()
//                            .hasRole("ADMINISTRATIEFMEDEWERKER")
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

                    .anyRequest().authenticated())
            .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
            .csrf().disable();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(medewerkerService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withPublicKey(jwtConfigProperties.getPublicKey()).build();
    }

    @Bean
    public JwtEncoder jwtEncoder() {
        JWK jwk = new RSAKey.Builder(jwtConfigProperties.getPublicKey())
                .privateKey(jwtConfigProperties.getPrivateKey())
                .build();
        JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
        return new NimbusJwtEncoder(jwks);
    }

}
