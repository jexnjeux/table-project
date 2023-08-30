package com.example.table.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Bean
  public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
    UserDetails user = User.withUsername("user")
        .password("{noop}password")
        .roles("USER")
        .build();

    UserDetails partner = User.withUsername("partner")
        .password("{noop}password")
        .roles("PARTNER")
        .build();

    return new InMemoryUserDetailsManager(user, partner);
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.csrf(AbstractHttpConfigurer::disable)
        .authorizeRequests(authorizeRequests ->
            authorizeRequests
                .requestMatchers(new AntPathRequestMatcher("/api/partner/**")).hasRole("PARTNER")
                .requestMatchers(new AntPathRequestMatcher("/api/user/**")).authenticated()
                .anyRequest().permitAll()
        );
    http.formLogin(httpSecurityFormLoginConfigurer -> httpSecurityFormLoginConfigurer
        .defaultSuccessUrl("/")
        .failureUrl("/login?error")
        .usernameParameter("username")
        .passwordParameter("password")
        .loginProcessingUrl("/api/login")
        .permitAll());
    http.headers(headers -> headers
        .frameOptions(FrameOptionsConfig::sameOrigin
        )
    );
    return http.build();
  }

  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

}
