package com.mr13.vacationschedule.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  private final UserDetailsService detailsService;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .csrf().disable()
        .authorizeRequests().anyRequest().authenticated()
        .and()
        .formLogin().permitAll();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(detailsService).passwordEncoder(encodePWD());
  }

  @Bean
  public BCryptPasswordEncoder encodePWD() {
    return new BCryptPasswordEncoder();
  }
}
