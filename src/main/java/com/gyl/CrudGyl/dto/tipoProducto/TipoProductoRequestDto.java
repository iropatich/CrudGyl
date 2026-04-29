package com.gyl.CrudGyl.dto.tipoProducto;

import jakarta.validation.constraints.NotBlank;

public record TipoProductoRequestDto (
    @NotBlank(message = "El nombre es obligatorio")
    String nombre,

    @NotBlank(message = "La descripcion es obligatoria")
    String descripcion
){

}
