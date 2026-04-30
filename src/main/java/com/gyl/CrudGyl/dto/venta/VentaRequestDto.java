package com.gyl.CrudGyl.dto.venta;

import com.gyl.CrudGyl.dto.detalleVenta.DetalleVentaRequestDto;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record VentaRequestDto(
        @NotNull
        Long idCliente,

        List<DetalleVentaRequestDto> detallesVenta
) {
}
