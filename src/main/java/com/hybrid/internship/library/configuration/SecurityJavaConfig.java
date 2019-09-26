package com.hybrid.internship.library.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.Properties;

@Configuration
//@EnableGlobalMethodSecurity(prePostEnabled=true)
@EnableWebSecurity
public class SecurityJavaConfig extends WebSecurityConfigurerAdapter {

    //    @Autowired
//    private MyUserDetailsService myUserDetailsService;
//    private final MyUserPrincipal myUserPrincipal;
    private final UserDetailsService userDetailsService;
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    public SecurityJavaConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //auth.userDetailsService(myUserDetailsService).passwordEncoder(passwordEncoder());
//        auth.jdbcAuthentication()
//                .usersByUsernameQuery("select username, password from user where username=?")
//                .authoritiesByUsernameQuery("select username, role from user where username=?")
//                .passwordEncoder(passwordEncoder());
        auth.inMemoryAuthentication()
                .withUser("admin").password(passwordEncoder().encode("admin")).roles("ADMIN")
                .and()
                .withUser("nikolasavic").password(passwordEncoder().encode("sifra")).roles("USER");
        auth.userDetailsService(inMemoryUserDetailsManager());
        //auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(restAuthenticationEntryPoint)
                .and()
                .headers().frameOptions().disable()
                .and()
                .authorizeRequests()
                .antMatchers("/api/user/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "api/book/**").authenticated()
                .antMatchers(HttpMethod.POST, "/api/book/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/book/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/book/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "api/book-copy/**").authenticated()
                .antMatchers(HttpMethod.POST, "/api/book-copy/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/book-copy/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/book-copy/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/book-rental/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/api/book-rental/rent-book").authenticated()
                .antMatchers(HttpMethod.PUT, "/api/book-rental/return-book").authenticated()
                .antMatchers(HttpMethod.DELETE, "/api/book-rental/**").hasRole("ADMIN")
                .antMatchers("/swagger-ui.html").permitAll()


//                .antMatchers(HttpMethod.GET,"/api/book-rental/most-rented/**").hasRole("ADMIN")
//                .antMatchers(HttpMethod.GET,"/api/book-rental/overdue-book-returns/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                //.successHandler(mySuccessHandler)
                //.failureHandler(myFailureHandler)
                .and()
                .httpBasic()
                .and()
                .logout();

    }

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager(){
        final Properties users = new Properties();
        users.put("user","pass,ROLE_USER,enabled");
        return new InMemoryUserDetailsManager(users);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
