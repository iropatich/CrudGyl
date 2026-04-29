package com.gyl.CrudGyl.controller;

import com.gyl.CrudGyl.dto.tipoProducto.TipoProductoRequestDto;
import com.gyl.CrudGyl.dto.tipoProducto.TipoProductoResponseDto;
import com.gyl.CrudGyl.service.interfaz.TipoProductoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tiposProducto")
public class TipoProductoController {
    private final TipoProductoService tipoProductoService;

    public TipoProductoController(TipoProductoService tipoProductoService) {
        this.tipoProductoService = tipoProductoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TipoProductoResponseDto crear(@Valid @RequestBody TipoProductoRequestDto dto) {
        return tipoProductoService.crear(dto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TipoProductoResponseDto> listar() {
        return tipoProductoService.listar();
    }

    @GetMapping("/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TipoProductoResponseDto buscarPorId(@PathVariable Long id) {
       return tipoProductoService.buscarPorId(id);
    }

    @GetMapping("/nombre/{nombre}")
    @ResponseStatus(HttpStatus.OK)
    public List<TipoProductoResponseDto> buscarNombre(@PathVariable String nombre) {
        return tipoProductoService.buscarNombre(nombre);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public TipoProductoResponseDto actualizar(@PathVariable Long id, @Valid @RequestBody TipoProductoRequestDto dto) {
        return tipoProductoService.actualizar(id, dto);
    }

    @DeleteMapping("/id/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        tipoProductoService.eliminar(id);
    }
}
