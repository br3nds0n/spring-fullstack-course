package br.com.studies.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.studies.spring.security.JwtRequestFilter;
import br.com.studies.spring.security.jwtAuthenticationEntryPoint;

@Configuration
public class WebSecurityConfig {

    @Autowired
	private jwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
	private JwtRequestFilter jwtRequestFilter;

    @Bean
    public BCryptPasswordEncoder bCryptPassword() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/swagger-ui/**", "/swagger-resources/**", "/v2/api-docs/**").permitAll()
                .antMatchers(HttpMethod.POST, "/users", "/login").permitAll()
                .anyRequest().authenticated()
                .and().cors()
                .and().exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);		

        return http.build();
    }
}
