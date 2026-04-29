package com.gyl.CrudGyl.controller;

import com.gyl.CrudGyl.dto.producto.ProductoRequestDto;
import com.gyl.CrudGyl.dto.producto.ProductoResponseDto;
import com.gyl.CrudGyl.service.interfaz.ProductoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {
    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductoResponseDto crear(@Valid @RequestBody ProductoRequestDto dto) {
        return productoService.crear(dto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductoResponseDto> listar() {
        return productoService.listar();
    }

    @GetMapping("/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductoResponseDto buscarPorId(@PathVariable Long id) {
        return productoService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductoResponseDto actualizar (@PathVariable Long id, @Valid @RequestBody ProductoRequestDto dto) {
        return productoService.actualizar(id, dto);
    }

    @DeleteMapping("/id/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        productoService.eliminar(id);
    }

    @GetMapping("/nombre/{nombre}")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductoResponseDto> buscarNombre(@Valid @PathVariable String nombre) {
        return productoService.buscarNombre(nombre);
    }
}
