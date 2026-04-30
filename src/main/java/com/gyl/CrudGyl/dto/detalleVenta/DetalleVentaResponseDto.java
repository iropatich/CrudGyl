package com.gyl.CrudGyl.dto.detalleVenta;

public record DetalleVentaResponseDto(
        Integer cantidad,
        Double precioUnitario,
        Double subtotal,
        String producto
) {
}
