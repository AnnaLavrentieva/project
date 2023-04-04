//package com.lavrentieva.config;
//
//import com.lavrentieva.repository.PersonRepository;
//import com.lavrentieva.service.PersonService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class SecurityConfig {
//    private final PersonRepository personRepository;
//
//    public SecurityConfig(PersonRepository personRepository) {
//        this.personRepository = personRepository;
//    }
//
//
//
//    @Bean
//    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
//        return http
//                .csrf(AbstractHttpConfigurer::disable)
//                .authorizeRequests(authorize -> {
//                    authorize.antMatchers("/").permitAll();
//                    authorize.antMatchers("/invoice").hasRole("ADMIN");
//                    authorize.antMatchers("/warehouse").hasRole("ADMIN");
//                    authorize.antMatchers("/recorder").hasRole("ADMIN");
//                    authorize.antMatchers("/ware").authenticated();
//                    authorize.antMatchers("/person").authenticated();
//                    authorize.antMatchers("/group").authenticated();
//                })
//                .formLogin()
//                .and()
//                .build();
//    }
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public UserDetailsService userDetailsService(){
//        return new PersonService(personRepository,passwordEncoder());
//    }
//
//    @Bean
//    public AuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
//        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
//        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
//        return daoAuthenticationProvider;
//    }
//}
