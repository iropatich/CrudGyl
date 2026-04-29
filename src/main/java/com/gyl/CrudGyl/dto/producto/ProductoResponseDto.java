package com.gyl.CrudGyl.dto.producto;

public record ProductoResponseDto (
        Long id,
        String nombre,
        Double precio,
        Integer Stock
){

}
