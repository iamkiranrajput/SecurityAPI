package com.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;


@Configuration		//this annotation this calls provide configuration of application
@EnableWebSecurity  //to enable web security features in the application
@EnableMethodSecurity
public class RoleBasedSecurity{

    @Autowired
    DataSource dataSource;
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((request)->request.requestMatchers("h2-console/**").permitAll()
                .anyRequest().authenticated());
        //any request will be authenticated
        http.sessionManagement(session
                ->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

//		http.formLogin();
        http.httpBasic();   // basic authentication with default settings
            return http.build();

    }


   @Bean
    public UserDetailsService userDetailsService(){
        UserDetails user1= User.withUsername("user").password( passwordEncoder().encode("user")).roles("USER").build();



        UserDetails admin=User.withUsername("admin").password(passwordEncoder().encode("admin")).roles("ADMIN").build();


        JdbcUserDetailsManager userDetailsManager=new JdbcUserDetailsManager(dataSource);

        userDetailsManager.createUser(user1);
        userDetailsManager.createUser(admin);
        return userDetailsManager;


        //        return new InMemoryUserDetailsManager(user1,admin,admin2);
    }

            @Bean
            public PasswordEncoder passwordEncoder(){
            return new BCryptPasswordEncoder();
            }
}
