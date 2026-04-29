package com.gyl.CrudGyl.service.impl;

import com.gyl.CrudGyl.dto.producto.ProductoRequestDto;
import com.gyl.CrudGyl.dto.producto.ProductoResponseDto;
import com.gyl.CrudGyl.entity.Producto;
import com.gyl.CrudGyl.entity.TipoProducto;
import com.gyl.CrudGyl.exception.RecursosNoEncontradoException;
import com.gyl.CrudGyl.mapper.ProductoMapper;
import com.gyl.CrudGyl.repository.ProductoRepository;
import com.gyl.CrudGyl.repository.TipoProductoRepository;
import com.gyl.CrudGyl.service.interfaz.ProductoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService {


    private ProductoRepository productoRepository;
    private TipoProductoRepository tipoProductoRepository;

    public ProductoServiceImpl(ProductoRepository productoRepository, TipoProductoRepository tipoProductoRepository) {
        this.productoRepository = productoRepository;
        this.tipoProductoRepository = tipoProductoRepository;
    }

    @Override
    public ProductoResponseDto crear(ProductoRequestDto dto) {
        TipoProducto tipoProducto = tipoProductoRepository.findById(dto.idTipoProducto())
                .orElseThrow(() -> new RecursosNoEncontradoException(
                        "No se encontro el tipo de producto con id " + dto.idTipoProducto()
                ));

        Producto producto = ProductoMapper.toEntity(dto, tipoProducto);
        Producto guardado = productoRepository.save(producto);
        return ProductoMapper.toResponseDto(guardado);
    }

    @Override
    public List<ProductoResponseDto> listar() {
        return productoRepository.findAll()
                .stream()
                .map(ProductoMapper::toResponseDto)
                .toList();
    }

    @Override
    public ProductoResponseDto buscarPorId(Long id) {
        return productoRepository.findById(id)
                .map(ProductoMapper::toResponseDto)
                .orElseThrow(() -> new RecursosNoEncontradoException(
                        "no se encontro el Id " + id
                ));
    }

    @Override
    public ProductoResponseDto actualizar(Long id, ProductoRequestDto dto) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RecursosNoEncontradoException(
                        "No se encontró el id " + id
                ));

        TipoProducto tipoProducto = tipoProductoRepository.findById(dto.idTipoProducto())
                .orElseThrow(() -> new RecursosNoEncontradoException(
                        "No se encontro el tipo de producto con id " + dto.idTipoProducto()
                ));

        ProductoMapper.updateEntity(producto, dto, tipoProducto);
        Producto guardado = productoRepository.save(producto);
        return ProductoMapper.toResponseDto(guardado);
    }

    @Override
    public void eliminar(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RecursosNoEncontradoException(
                        "No se encontró el id" + id
                ));

        productoRepository.delete(producto);
    }

    @Override
    public List<ProductoResponseDto> buscarNombre(String nombre) {
        List<ProductoResponseDto> productos = productoRepository.findByNombre(nombre)
                .stream()
                .map(ProductoMapper::toResponseDto)
                .toList();
        if (productos.isEmpty()) {
            throw new RecursosNoEncontradoException(
                    "No se encontraron productos con nombre " + nombre
            );
        }
        return productos;
    }
}
