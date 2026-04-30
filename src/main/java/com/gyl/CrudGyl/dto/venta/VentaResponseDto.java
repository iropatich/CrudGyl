package com.gyl.CrudGyl.dto.venta;

import com.gyl.CrudGyl.dto.detalleVenta.DetalleVentaResponseDto;

import java.time.LocalDateTime;
import java.util.List;

public record VentaResponseDto(
        Long id,
        LocalDateTime fecha,
        String cliente,
        Double total,
        List<DetalleVentaResponseDto> detallesVenta
) {
}
