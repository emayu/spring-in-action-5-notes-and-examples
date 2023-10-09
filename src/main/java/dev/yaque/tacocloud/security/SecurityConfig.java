/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.yaque.tacocloud.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

/**
 *
 * @author yaque
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter{
    
    

//    @Bean
//    public InMemoryUserDetailsManager userDetailsService(){
//        UserDetails user = User.withDefaultPasswordEncoder()
//                .username("buzz")
//                .password("infinity")
//                .roles("USER")
//                .build();
//        return new InMemoryUserDetailsManager(user);
//    }
    
    @Autowired
    private UserDetailsService userDetailsService;
    
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
           .authorizeRequests()
                .antMatchers("/design", "/orders")
                    .access("hasRole('USER')")
                .antMatchers("/", "/**").permitAll()
            .and()
                .formLogin()
                    .loginPage("/login")
//                    .loginProcessingUrl("/authenticate")
//                    .usernameParameter("user")
//                    .passwordParameter("pwd")
            .and()
                .logout()
                .logoutSuccessUrl("/")
            .and()
                .csrf()
                .ignoringAntMatchers("/h2-console/**")
            .and()  
                .headers()
                .frameOptions()
                .sameOrigin();
        
    }
    
    
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(encoder());
    }

    
    
    @Bean
    public PasswordEncoder encoder(){
        return new StandardPasswordEncoder("5up3er53cr3t");
    }
    
}
