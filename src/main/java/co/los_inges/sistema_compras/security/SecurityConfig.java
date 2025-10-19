package co.los_inges.sistema_compras.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Desactiva CSRF (para APIs REST) y habilita CORS (para Angular)
                .csrf(csrf -> csrf.disable())
                .cors(Customizer.withDefaults())

                //  Configuración de rutas
                .authorizeHttpRequests(auth -> auth

                        // Endpoints públicos (sin token JWT)
                        .requestMatchers(
                                "/auth/**",
                                "/api/auth/**",
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/public/**",
                                "/api/ciudades/**",
                                "/api/categorias/**",
                                "/api/marcas/**",
                                "/api/unidades-medida/**"
                        ).permitAll()

                        // Endpoints accesibles para cualquier usuario autenticado (JWT válido)
                        .requestMatchers(
                                "/api/compras/**",
                                "/api/detalle-compras/**",
                                "/api/proveedores/**",
                                "/api/productos/**",
                                "/api/impuestos/**"
                        ).authenticated()

                        //  Endpoints restringidos a administradores
                        .requestMatchers(
                                "/api/usuarios/**",
                                "/api/roles/**"
                        ).hasRole("ADMIN")

                        // Cualquier otra ruta requiere autenticación
                        .anyRequest().authenticated()
                )

                // Sin sesiones en servidor (modo stateless para JWT)
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                // Authentication Provider personalizado
                .authenticationProvider(authenticationProvider)

                // Inserta el filtro JWT antes del filtro estándar de autenticación
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    //  CORS: permite llamadas desde cualquier origen (útil para Angular)
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOriginPattern("*");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}

