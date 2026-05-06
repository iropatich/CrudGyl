package com.gyl.CrudGyl.dto.cliente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ClienteRequestDto(
        @NotBlank(message = "El nombre es obligatorio")
        String nombre,

        @NotBlank(message = "El apellido es obligatorio")
        String apellido,

        @NotBlank(message = "El correo es obligatorio")
        @Email(message = "El formato del correo no es correcto")
        String correo,

        @NotBlank(message = "El telefono es obligatorio")
        String telefono,

        @NotBlank(message = "La direccion es obligatoria")
        String direccion
) {

}
