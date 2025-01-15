package org.gestionare_taskuri.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

/*
 * UserDetailsService bean is provided by the ScrumUserDetailsServiceImpl
 * UNCOMMENT @Service annotation
 */
@Configuration @EnableWebSecurity
public class SecurityJpaConfiguration {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // full path from context-path
        // full path from rest.basePath
        return http
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/rest/**").hasAuthority("MEMBER")
                        .requestMatchers("/data/projects/**").hasAuthority("MEMBER")
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                //.csrf((csrf) -> csrf.ignoringRequestMatchers("/data/projects/**"))
                .build();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}