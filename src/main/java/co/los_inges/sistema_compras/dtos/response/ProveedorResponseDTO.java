package co.los_inges.sistema_compras.dtos.response;

import java.util.List;

public record ProveedorResponseDTO (Long idProveedor,
                                    String nombre,
                                    CiudadResponseDTO ciudad,
                                    String direccion,
                                    String email,
                                    boolean estado,
                                    List<String> telefonos){}
