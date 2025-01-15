package org.gestionare_taskuri.security;
import org.springframework.context.annotation.Bean;

import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/*
 * When multiple UserDetailsService beans provided -> DEACTIVATE others
 * (e.g. ScrumUserDetailsServiceImpl: comment @Service annotation)
 */

//@Configuration @EnableWebSecurity
public class BasicConfiguration {

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails user = org.springframework.security.core.userdetails.User.withUsername("developer")
                .password(passwordEncoder.encode("msd"))
                //.authorities("ROLE_MEMBER")
                .roles("MEMBER")
                .build();

        UserDetails admin = org.springframework.security.core.userdetails.User.withUsername("admin")
                .password(passwordEncoder.encode("admin"))
                //.authorities("ROLE_MEMBER", "ROLE_ADMIN")
                .roles("MEMBER", "ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // full path from context-path
        // full path from rest.basePath
        return http
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/rest/**").hasRole("MEMBER")
                        .requestMatchers("/data/projects/**").hasRole("MEMBER")
                        //.requestMatchers(HttpMethod.GET, "/data/projects/**").hasRole("MEMBER")
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
/*
Docs:
* https://docs.spring.io/spring-security/reference/servlet/architecture.html
* https://docs.spring.io/spring-security/reference/servlet/authentication/passwords/index.html#servlet-authentication-unpwd
* https://docs.spring.io/spring-security/reference/servlet/authorization/authorize-http-requests.html
* https://docs.spring.io/spring-security/reference/servlet/authorization/authorize-http-requests.html
* https://docs.spring.io/spring-security/reference/servlet/authentication/passwords/dao-authentication-provider.html
* https://springframework.guru/spring-boot-web-application-part-6-spring-security-with-dao-authentication-provider/

* https://stackoverflow.com/questions/51079564/spring-security-antmatchers-not-being-applied-on-post-requests-and-only-works-wi
* https://docs.spring.io/spring-security/reference/servlet/exploits/csrf.html

hasRole - A shortcut for hasAuthority that prefixes ROLE_ or whatever is configured as the default prefix
.requestMatchers("/rest/**").hasRole("MEMBER")  for ROLE_MEMBER authority
*/