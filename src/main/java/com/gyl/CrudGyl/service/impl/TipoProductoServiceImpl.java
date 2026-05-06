package com.gyl.CrudGyl.service.impl;

import com.gyl.CrudGyl.dto.tipoProducto.TipoProductoRequestDto;
import com.gyl.CrudGyl.dto.tipoProducto.TipoProductoResponseDto;
import com.gyl.CrudGyl.entity.TipoProducto;
import com.gyl.CrudGyl.exception.ProductosActivosException;
import com.gyl.CrudGyl.exception.RecursosNoEncontradoException;
import com.gyl.CrudGyl.mapper.TipoProductoMapper;
import com.gyl.CrudGyl.repository.ProductoRepository;
import com.gyl.CrudGyl.repository.TipoProductoRepository;
import com.gyl.CrudGyl.service.interfaz.TipoProductoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoProductoServiceImpl implements TipoProductoService {

    private TipoProductoRepository tipoProductoRepository;
    private ProductoRepository productoRepository;

    public TipoProductoServiceImpl(TipoProductoRepository tipoProductoRepository, ProductoRepository productoRepository) {
        this.tipoProductoRepository = tipoProductoRepository;
        this.productoRepository = productoRepository;
    }

    @Override
    public TipoProductoResponseDto crear(TipoProductoRequestDto dto) {
        TipoProducto tipoProducto = TipoProductoMapper.toEntity(dto);
        TipoProducto guardado = tipoProductoRepository.save(tipoProducto);
        return TipoProductoMapper.toResponseDto(guardado);
    }

    @Override
    public List<TipoProductoResponseDto> listar() {
        return tipoProductoRepository.findAllByEstadoTrue()
                .stream()
                .map(TipoProductoMapper::toResponseDto)
                .toList();
    }

    @Override
    public List<TipoProductoResponseDto> buscarNombre(String nombre) {
        List<TipoProductoResponseDto> tiposDeProducto = tipoProductoRepository.findByNombreAndEstadoTrue(nombre)
                .stream()
                .map(TipoProductoMapper::toResponseDto)
                .toList();
        if (tiposDeProducto.isEmpty()) {
            throw new RecursosNoEncontradoException(
                    "No se encontro el tipo de producto con nombre " + nombre
            );
        }
        return tiposDeProducto;
    }

    @Override
    public TipoProductoResponseDto buscarPorId(Long id) {
        return tipoProductoRepository.findByIdAndEstadoTrue(id)
                .map(TipoProductoMapper::toResponseDto)
                .orElseThrow(() -> new RecursosNoEncontradoException(
                        "No se encontro el Id " + id
                ));
    }

    @Override
    public TipoProductoResponseDto actualizar(Long id, TipoProductoRequestDto dto) {
        TipoProducto tipoProducto = tipoProductoRepository.findById(id)
                .orElseThrow(() -> new RecursosNoEncontradoException(
                        "No se encontro el Id " + id
                ));
        TipoProductoMapper.updateEntity(tipoProducto, dto);
        TipoProducto guardado = tipoProductoRepository.save(tipoProducto);
        return TipoProductoMapper.toResponseDto(guardado);
    }

    @Override
    public void eliminar(Long id) {
        TipoProducto tipoProducto = tipoProductoRepository.findByIdAndEstadoTrue(id)
                .orElseThrow(() -> new RecursosNoEncontradoException(
                        "No se encontro el id " + id
                ));

        validarProductosActivos(tipoProducto);
        tipoProducto.setEstado(false);
        tipoProductoRepository.save(tipoProducto);
    }

    private void validarProductosActivos(TipoProducto tipoProducto) {
        boolean tieneProductosActivos = productoRepository.existsByTipoProductoAndEstadoTrue(tipoProducto);

        if (tieneProductosActivos) {
            throw new ProductosActivosException(
                    "No se puede eliminar el tipo de producto porque tiene productos activos"
            );
        }
    }
}
