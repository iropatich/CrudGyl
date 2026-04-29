package com.gyl.CrudGyl.dto;

public record ProductoResponseDto (
        Long id,
        String nombre,
        Double precio,
        Integer Stock
){

}
