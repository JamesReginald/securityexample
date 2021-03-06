package com.jimmiereggievanvliet.springSecurityfull.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.jimmiereggievanvliet.springSecurityfull.security.UserRole.*;
import static com.jimmiereggievanvliet.springSecurityfull.security.UserPermissions.*;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityConfiguration(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "index", "/css/*", "/js/*").permitAll()
                .antMatchers("/users/**").hasRole(USER.name())
//                .antMatchers(HttpMethod.DELETE,"/management/**").hasAuthority(COURSE_WRITE.getPermission())
//                .antMatchers(HttpMethod.POST,"/management/**").hasAuthority(COURSE_WRITE.getPermission())
//                .antMatchers(HttpMethod.PUT,"/management/**").hasAuthority(COURSE_WRITE.getPermission())
//                .antMatchers(HttpMethod.GET,"/management/**").hasAnyRole(ADMIN.name(), ADMINTRAINEE.name())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }
    @Override
    @Bean
    public UserDetailsService userDetailsServiceBean() throws Exception {
         UserDetails testUser= User.builder()
                .username("user")
                .password(passwordEncoder.encode("user"))
                 .authorities(USER.getGrantedAuthorities())
//                roles(USER.name()) // ROLE_USER
                 .build();

         UserDetails adminUser = User.builder()
                 .username("admin")
                 .password(passwordEncoder.encode("admin"))
                 .authorities(ADMIN.getGrantedAuthorities())
//                 .roles(ADMIN.name()) // ROLE_ADMIN
                 .build();
        UserDetails adminTrainneeUser = User.builder()
                .username("adminT")
                .password(passwordEncoder.encode("admin"))
                .authorities(ADMINTRAINEE.getGrantedAuthorities())
//                .roles(ADMINTRAINEE.name()) // ROLE_ADMINTRAINEE
                .build();
         return new InMemoryUserDetailsManager(
                 testUser,
                 adminUser,
                 adminTrainneeUser
         );
    }


}
