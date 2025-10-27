package com.blogger.config;

// import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableWebSecurity // for enabling web security in the application with customizations
public class SpringSecurity {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http.authorizeHttpRequests(request -> request
                .requestMatchers("/admin/**")   //role based access control
                .hasRole("ADMIN")
                .requestMatchers("/blog/**", "/admin/**", "/user/delete", "/user/update", "/user/getUser", "/externalUsers/**", "/email/**") // secured endpoints, will need to pass user and password
                .authenticated()
                .anyRequest().permitAll())
                .httpBasic(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable) // diabled because we do not giving crsf token in postman
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
