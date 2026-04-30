package com.gyl.CrudGyl.dto.detalleVenta;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record DetalleVentaRequestDto(
        @NotNull
        @Min(value = 1, message = "Se debe vender minimo 1 producto")
        Integer cantidad,

        @NotNull
        Long idProducto
) {
}
