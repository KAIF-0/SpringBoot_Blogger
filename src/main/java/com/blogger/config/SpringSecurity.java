package com.blogger.config;

import org.springframework.beans.factory.annotation.Autowired;

// import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.client.RestTemplate;

import com.blogger.filter.JwtFilter;

@Configuration
@EnableWebSecurity // for enabling web security in the application with customizations
public class SpringSecurity {

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http.authorizeHttpRequests(request -> request
                .requestMatchers("/admin/**") // role based access control
                .hasRole("ADMIN")
                .requestMatchers("/blog/**", "/admin/**", "/user/delete", "/user/update", "/user/getUser",
                        "/externalUsers/**", "/email/**", "/auth/verify") // secured endpoints, will need to pass user and password
                .authenticated()
                .anyRequest().permitAll())
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class) //adding jwt filter for jwt token validation
                .httpBasic(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable) // disabled because we do not giving crsf token in postman
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    // for testing purpose only with fixed user creds
    // @Bean
    // public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder)
    // {
    // UserDetails user = User.builder()
    // .username("user")
    // .password(passwordEncoder.encode("test123"))
    // .roles("USER")
    // .build();

    // return new InMemoryUserDetailsManager(user);
    // }

}
