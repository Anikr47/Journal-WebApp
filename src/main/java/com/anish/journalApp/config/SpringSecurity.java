package com.anish.journalApp.config;

import com.anish.journalApp.service.UserDetailServiceIMPL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
@Profile("dev")
public class SpringSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailServiceIMPL userDetailService;
     @Override
      protected void configure(HttpSecurity http) throws Exception{
           http.authorizeRequests()
                   .antMatchers("/journal/**","/user/**").authenticated()
                   .antMatchers("/admin/**").hasAnyRole("ADMIN")
                   .anyRequest().permitAll()
                   .and()
                   .httpBasic();
           http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().csrf().disable();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
         return new BCryptPasswordEncoder();
    }
    @Override
    protected  void configure(AuthenticationManagerBuilder auth) throws Exception{
         auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder());

    }

}
