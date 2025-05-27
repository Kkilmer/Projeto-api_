package br.com.alunoonline.api.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity//ativa o SpringS.
public class SecurityConfigurations {

    @Autowired
    private SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain
    securityFilterChain(HttpSecurity http)
            throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                //define que as sessões são stateless
                .sessionManagement(sm -> sm.sessionCreationPolicy
                        (SessionCreationPolicy.STATELESS))

                //libera login, register e swagger, bloqueia as demais
                .authorizeHttpRequests(req-> {
                    req.requestMatchers("/login").permitAll();
                    req.requestMatchers("/register").permitAll();
                    req.requestMatchers(
                            "/swagger-ui/**",
                            "/v3/api-docs/**",
                            "/swagger-resources/**",
                            "/swagger-resources",
                            "/webjars/**"
                    ).permitAll();
                    req.anyRequest().authenticated();
                })
                //verifica o JWT
                .addFilterBefore(securityFilter,
                        UsernamePasswordAuthenticationFilter.class)
                .build();
    }
    @Bean
    public AuthenticationManager authenticationManager
            (AuthenticationConfiguration configuration)
            throws Exception{
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return  new BCryptPasswordEncoder();
    }
}

