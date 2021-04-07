/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.negocio.configuaration;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mariela
 */


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
// http://docs.spring.io/spring-boot/docs/current/reference/html/howto-security.html
// Switch off the Spring Boot security configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    //@Autowired
    //private AccessDeniedHandler accessDeniedHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //configuracion en si
        http.csrf().disable()
                //para distintos matches le damos los roles...
                .authorizeRequests()
                .antMatchers("/", "/home", "/about").permitAll()
                .antMatchers("/admin/**").hasAnyRole("ADMIN")
                //sabemos que user por ej. porque lo vemos en metodo configureGlobal
                .antMatchers("/user/**").hasAnyRole("USER")
                //para cualquier usuario debe estar autenticado
                .anyRequest().authenticated()
                .and()
                //para /login permitimos a todos
                .formLogin().loginPage("/login").permitAll()
                .and()
                .logout()
                .permitAll();
                //.and()
               // .exceptionHandling().accessDeniedHandler(accessDeniedHandler);
    }


    @Autowired// Aqui tenemos accesos todos.
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
               .withUser("user").password("password").roles("USER")
               .and()
               .withUser("admin").password("password").roles("ADMIN");
    }

}