package com.gyl.CrudGyl.mapper;

import com.gyl.CrudGyl.dto.producto.ProductoRequestDto;
import com.gyl.CrudGyl.dto.producto.ProductoResponseDto;
import com.gyl.CrudGyl.entity.Producto;
import com.gyl.CrudGyl.entity.TipoProducto;

public class ProductoMapper {
    private ProductoMapper() {

    }

    public static Producto toEntity(ProductoRequestDto dto, TipoProducto tipoProducto) {
        Producto producto = new Producto();
        producto.setNombre(dto.nombre());
        producto.setPrecio(dto.precio());
        producto.setStock(dto.stock());
        producto.setTipoProducto(tipoProducto);

        return producto;
    }

    public static ProductoResponseDto toResponseDto(Producto producto) {
        return new ProductoResponseDto(
                producto.getId(),
                producto.getNombre(),
                producto.getPrecio(),
                producto.getStock(),
                producto.getTipoProducto().getNombre()
        );
    }

    public static void updateEntity(Producto producto, ProductoRequestDto dto, TipoProducto tipoProducto) {
        producto.setNombre(dto.nombre());
        producto.setPrecio(dto.precio());
        producto.setStock(dto.stock());
        producto.setTipoProducto(tipoProducto);
    }
}
