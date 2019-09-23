package com.hybrid.internship.library.configuration;

import com.hybrid.internship.library.services.serviceImplementation.MyUserDetailsService;
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

@Configuration
@EnableWebSecurity
public class SecurityJavaConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailsService myUserDetailsService;
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    public SecurityJavaConfig(MyUserDetailsService myUserDetailsService){
        this.myUserDetailsService = myUserDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService).passwordEncoder(passwordEncoder());
        auth.inMemoryAuthentication()
                .withUser("admin").password(passwordEncoder().encode("admin")).roles("ADMIN")
                .and()
                .withUser("nikolasavic").password(passwordEncoder().encode("sifra")).roles("USER");
    }

    protected void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(restAuthenticationEntryPoint)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,"/api/user").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/api/book").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT,"/api/book").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE,"/api/book").hasRole("ADMIN")
                //.antMatchers(HttpMethod.GET,"api/book").authenticated()
                //.antMatchers(HttpMethod.POST,"api/book-rental/rent-book").authenticated()
                //.antMatchers(HttpMethod.PUT,"api/book-rental/return-book").authenticated()
                .antMatchers(HttpMethod.GET,"api/book-rental/most-rented").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"api/book-rental/overdue-book-returns").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                //.successHandler(mySuccessHandler)
                //.failureHandler(myFailureHandler)
                .and()
                .logout();

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
