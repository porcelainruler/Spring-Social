package com.shubham.project.spring_network.config;


import com.shubham.project.spring_network.security.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtAuthenticationEntryPoint point;
    @Autowired
    private JwtAuthFilter filter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // ! Old filter chain with ui based basic auth
        //        http.csrf(AbstractHttpConfigurer::disable)
        //                .cors(cors -> {cors.configurationSource(corsConfigurationSource());})
        //                .authorizeHttpRequests(auth -> {
        //                    auth.requestMatchers("/login", "/user", "api/v1/auth/generateToken")
        //                            .permitAll()
        //                            .anyRequest()
        //                            .authenticated();
        //                })
        //                .formLogin(Customizer.withDefaults())
        //                .logout(logout -> logout.permitAll()
        //                )
        //                .exceptionHandling(configurer ->
        //                        configurer.accessDeniedPage("/access-denied")
        //                );;

        // ! Filter chain for JAVA jwt based Backend Service
        http.csrf(csrf -> csrf.disable())
                .authorizeRequests().
                requestMatchers("/test").authenticated().requestMatchers("api/v1/auth/login").permitAll()
                .anyRequest()
                .authenticated()
                .and().exceptionHandling(ex -> ex.authenticationEntryPoint(point))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
//                .formLogin((formLogin) -> formLogin.loginPage("/login")
////                        .defaultSuccessUrl("/swagger-ui/index.html#/")
//                        .defaultSuccessUrl("/")
//                        .failureUrl("/login?error=true")
////                        .successHandler(myAuthenticationSuccessHandler)
////                        .failureHandler(authenticationFailureHandler)
////                        .authenticationDetailsSource(authenticationDetailsSource)
//                        .permitAll());

    }

    @Bean
    public RoleHierarchy getHierarchy () {
        RoleHierarchyImpl roleHierarchyImpl = new RoleHierarchyImpl();
        String hierarchy = "ROLE_ADMIN > ROLE_MODERATOR \n ROLE_MODERATOR > ROLE_MEMBER";

        roleHierarchyImpl.setHierarchy(hierarchy);

        return roleHierarchyImpl;
    }

    /* *---------------------------------------- Pwd Encoder Beans ------------------------------------------------- */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(11);
    }

    /* *--------------------------------------- Authentication Beans ------------------------------------------------ */
    //    @Bean
    //    public UserDetailsService userDetailsService () {
    //        return new CustomUserDetailsService();
    //    }

    /* *--------------------------------------- Authentication Beans ------------------------------------------------ */
    @Bean
    public AuthenticationProvider authenticationProvider () {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    /* *--------------------------------------------- CORS Beans --------------------------------------------------- */
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
