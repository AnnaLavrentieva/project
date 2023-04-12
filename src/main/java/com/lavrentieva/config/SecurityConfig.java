package com.lavrentieva.config;

import com.lavrentieva.model.Person;
import com.lavrentieva.repository.PersonRepository;
import com.lavrentieva.service.JpaUserDetailsService;
import com.lavrentieva.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private final JpaUserDetailsService jpaUserDetailsService;

    public SecurityConfig(JpaUserDetailsService jpaUserDetailsService) {
        this.jpaUserDetailsService = jpaUserDetailsService;
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeRequests(authorize -> {
                    authorize.mvcMatchers("/").permitAll();
                    authorize.mvcMatchers("/home").authenticated();
                    authorize.mvcMatchers("/invoice").hasAuthority("ADMIN");
                    authorize.mvcMatchers("/recorder").hasAuthority("ADMIN"); //hasRole() має вбудований префікс "ROLE_" перед вказаною назвою ролі - НЕ ВИКОРИСТОВУЄМО!

                    authorize.mvcMatchers("/person/**").authenticated();
                    authorize.mvcMatchers("/group/**").authenticated();
                })
                .userDetailsService(jpaUserDetailsService)
                .headers(headers -> headers.frameOptions().sameOrigin())
                .formLogin()
                .successHandler((request, response, authentication) -> {
                    response.sendRedirect("/home");
                })
                .permitAll()
                .and()
                .rememberMe()
                .and()
                .logout().permitAll()
                .invalidateHttpSession(true)
                .and()
                .build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
