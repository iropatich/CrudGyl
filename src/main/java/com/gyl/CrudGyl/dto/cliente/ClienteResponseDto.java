package com.gyl.CrudGyl.dto.cliente;

public record ClienteResponseDto(
        Long id,
        String nombre,
        String apellido,
        String correo,
        String telefono,
        String direccion
) {

}
