package com.lavrentieva.config;

import com.lavrentieva.service.JpaUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private final JpaUserDetailsService jpaUserDetailsService;

    @Autowired
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
                    authorize.mvcMatchers("/item/**").authenticated();
                    authorize.mvcMatchers("/person/**").authenticated();
                    authorize.mvcMatchers("/group/**").authenticated();
                    authorize.mvcMatchers("/movement/**").authenticated();
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
