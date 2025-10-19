package co.los_inges.sistema_compras.security;

import co.los_inges.sistema_compras.dtos.request.LoginRequest;
import co.los_inges.sistema_compras.dtos.request.UsuarioRequestDTO;
import co.los_inges.sistema_compras.dtos.response.AuthResponseDTO;
import co.los_inges.sistema_compras.dtos.response.RolResponseDTO;
import co.los_inges.sistema_compras.dtos.response.UsuarioResponseDTO;
import co.los_inges.sistema_compras.models.Rol;
import co.los_inges.sistema_compras.models.Usuario;
import co.los_inges.sistema_compras.repositories.RolRepository;
import co.los_inges.sistema_compras.repositories.UsuarioRepository;
import co.los_inges.sistema_compras.security.JwtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Tag(name = "Autenticación", description = "Endpoints para iniciar sesión y registrar usuarios con JWT.")
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtService jwtService;
    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;

    // LOGIN
    @Operation(
            summary = "Iniciar sesión",
            description = """
                    Permite autenticar un usuario mediante su correo electrónico y contraseña.
                    Si las credenciales son válidas, devuelve un token JWT para usar en las peticiones protegidas.
                    """,
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = LoginRequest.class),
                            examples = @ExampleObject(value = """
                                    {
                                      "username": "micorreo@gmail.com",
                                      "password": "123"
                                    }
                                    """)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Autenticación exitosa. Devuelve el token JWT.",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = AuthResponseDTO.class),
                                    examples = @ExampleObject(value = """
                                            {
                                              "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
                                              "type": "Bearer",
                                              "expiresIn": 3600
                                            }
                                            """)
                            )
                    ),
                    @ApiResponse(responseCode = "401", description = "Credenciales inválidas (correo o contraseña incorrectos)"),
                    @ApiResponse(responseCode = "400", description = "Solicitud malformada")
            }
    )
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody @Valid LoginRequest request) {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password())
        );

        UserDetails user = (UserDetails) authentication.getPrincipal();

        String token = jwtService.generate(
                user.getUsername(),
                Map.of("roles", user.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .toList())
        );

        return ResponseEntity.ok(new AuthResponseDTO(token, "Bearer", jwtService.expiresInSeconds()));
    }

    // REGISTRO
    @Operation(
            summary = "Registrar un nuevo usuario",
            description = """
                    Crea un nuevo usuario en el sistema con el rol especificado.
                    """,
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioRequestDTO.class),
                            examples = @ExampleObject(value = """
                                    {
                                      "nombre": "Mi nombre",
                                      "email": "user@gmail.com",
                                      "telefono": "3001234567",
                                      "password": "123",
                                      "rolNombre": "ADMIN"
                                    }
                                    """)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Usuario registrado correctamente.",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = UsuarioResponseDTO.class))
                    ),
                    @ApiResponse(responseCode = "400", description = "Correo ya registrado o datos inválidos"),
                    @ApiResponse(responseCode = "404", description = "Rol especificado no existe")
            }
    )
    @PostMapping("/register")
    public ResponseEntity<UsuarioResponseDTO> register(@Valid @RequestBody UsuarioRequestDTO dto) {
        if (usuarioRepository.findByEmail(dto.email()).isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        String rolNombre = (dto.rolNombre() != null && !dto.rolNombre().isBlank())
                ? dto.rolNombre()
                : "USER";

        Rol rolAsignado = rolRepository.findByNombre(rolNombre)
                .orElseThrow(() -> new IllegalArgumentException("El rol '" + rolNombre + "' no existe."));

        Usuario nuevo = Usuario.builder()
                .nombre(dto.nombre())
                .email(dto.email())
                .telefono(dto.telefono())
                .password(passwordEncoder.encode(dto.password()))
                .rol(rolAsignado)
                .build();

        Usuario saved = usuarioRepository.save(nuevo);

        UsuarioResponseDTO response = new UsuarioResponseDTO(
                saved.getIdUsuario(),
                saved.getNombre(),
                saved.getEmail(),
                new RolResponseDTO(
                        saved.getRol().getIdRol(),
                        saved.getRol().getNombre(),
                        saved.getRol().getDescripcion()
                ),
                saved.getTelefono()
        );

        return ResponseEntity.created(URI.create("/api/usuarios/" + saved.getIdUsuario()))
                .body(response);
    }
}
