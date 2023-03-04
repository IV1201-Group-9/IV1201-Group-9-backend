package com.iv1201.recapp.Config;

import com.iv1201.recapp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

/**
 * Security Configuration class for defining instance of Spring Security
 */
@Configuration
@EnableWebSecurity
public class SecConfig {
    @Autowired
    private JwtFilter jwtFilter;
    @Autowired
    private UserService userService;

    /**
     * Bean for configuring the filter for spring Security. Sets up the filter when server
     * starts.
     * @param http for security configuring of http request
     * @return Builds a SecurityFilterChain with configuration for the application.
     * @throws Exception If something goes wrong
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors(Customizer.withDefaults()) // Uses the Bean CorsConfigurationSource
                .authorizeHttpRequests()
                .requestMatchers("/api/v1/auth/**").permitAll()
//                .requestMatchers("/api/v1/testEndpoint/**").permitAll()
                .requestMatchers("/api/v1/recruiters/**").hasAuthority(" recruiter")
//                .requestMatchers("/api/v1/recruiters/**").permitAll()
                .requestMatchers("/api/v1/getAllApplicants/**").hasAuthority(" applicant")
                .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider()) //Authentication provider
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
        ;
        return http.build();
    }

    /**
     * Bean for configuration of Cross-origin resource sharing in Spring application
     * @return <code>UrlBasedCorsConfigurationSource<code/> to be used for configuration
     * so that incoming data created by other source can be handled by server.
     */
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST","PATCH", "PUT", "DELETE", "OPTIONS", "HEAD"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(Arrays.asList("Authorization" , "Origin", "X-Requested-With", "Content-Type", "Accept"));
        configuration.setExposedHeaders(Arrays.asList("Authorization" , "Origin", "X-Requested-With", "Content-Type", "Accept"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    /**
     * Bean for configuration of Spring Security filter which provides authentication.
     * Is to be used by the <code>AuthenticationManager<code/> when authenticating
     * user credentials.
     * @return <code>daoAuthProvider<code/> for access of user information from
     * database.
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        final DaoAuthenticationProvider daoAuthProvider =
                new DaoAuthenticationProvider();

        daoAuthProvider.setPasswordEncoder(bcryptPasswordEncoder());
        daoAuthProvider.setUserDetailsService(this.userService);
        return daoAuthProvider;
    }

    /**
     * Bean for configuration of Authentication Manager to be used by Spring Security to
     * allocate authentication provider.
     * @param authConf the configured <code>AuthenticationConfiguration</code> set up by
     * Spring Security.
     * @return the <code>AuthenticationManager</code> from the
     * <code>AuthenticationConfiguration</code> set up by Spring Security.
     * @throws Exception if there were no <code>AuthenticationManager</code> to return
     * <code>AuthenticationConfiguration</code>
     */
    @Bean
    public AuthenticationManager authManager(AuthenticationConfiguration authConf) throws Exception {
        return authConf.getAuthenticationManager();
    }

    /**
     * Bean for the <code>PasswordEncoder</code> to be used by
     * Spring Security when entering new user or accessing user data.
     * @return the encoder object to be used.
     */
    @Bean
    public PasswordEncoder bcryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
