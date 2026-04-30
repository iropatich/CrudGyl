package com.gyl.CrudGyl.service.impl;

import com.gyl.CrudGyl.dto.detalleVenta.DetalleVentaRequestDto;
import com.gyl.CrudGyl.dto.venta.VentaRequestDto;
import com.gyl.CrudGyl.dto.venta.VentaResponseDto;
import com.gyl.CrudGyl.entity.Cliente;
import com.gyl.CrudGyl.entity.DetalleVenta;
import com.gyl.CrudGyl.entity.Producto;
import com.gyl.CrudGyl.entity.Venta;
import com.gyl.CrudGyl.exception.RecursosNoEncontradoException;
import com.gyl.CrudGyl.exception.StockInsuficienteException;
import com.gyl.CrudGyl.mapper.VentaMapper;
import com.gyl.CrudGyl.repository.ClienteRepository;
import com.gyl.CrudGyl.repository.ProductoRepository;
import com.gyl.CrudGyl.repository.VentaRepository;
import com.gyl.CrudGyl.service.interfaz.VentaService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class VentaServiceImpl implements VentaService {
    private VentaRepository ventaRepository;
    private ClienteRepository clienteRepository;
    private ProductoRepository productoRepository;

    public VentaServiceImpl(VentaRepository ventaRepository,  ClienteRepository clienteRepository, ProductoRepository productoRepository) {
        this.ventaRepository = ventaRepository;
        this.clienteRepository = clienteRepository;
        this.productoRepository = productoRepository;
    }

    @Override
    public VentaResponseDto crear(VentaRequestDto dto) {
        Cliente cliente = clienteRepository.findById(dto.idCliente())
                .orElseThrow(() -> new RecursosNoEncontradoException(
                        "No se ha encontrado el cliente con el id " + dto.idCliente()
                ));
        Venta venta = new Venta();
        venta.setFecha(LocalDateTime.now());
        venta.setCliente(cliente);

        List<DetalleVenta> listaDetallesVenta = new ArrayList<>();
        double total = 0;

        for (DetalleVentaRequestDto detalle : dto.detallesVenta()) {
            Producto producto = productoRepository.findById(detalle.idProducto())
                    .orElseThrow(() -> new RecursosNoEncontradoException(
                            "No se ha encontrado el producto con el id " + detalle.idProducto()
                    ));
            if (producto.getStock() < detalle.cantidad()) {
                throw new StockInsuficienteException(
                        "No hay suficiente stock del producto " + producto.getNombre()
                );
            }

            producto.setStock(producto.getStock() - detalle.cantidad());
            productoRepository.save(producto);

            double subtotal = producto.getPrecio() * detalle.cantidad();

            DetalleVenta detalleVenta = new DetalleVenta();
            detalleVenta.setCantidad(detalle.cantidad());
            detalleVenta.setPrecioUnitario(producto.getPrecio());
            detalleVenta.setSubtotal(subtotal);
            detalleVenta.setVenta(venta);
            detalleVenta.setProducto(producto);

            listaDetallesVenta.add(detalleVenta);
            total += subtotal;
        }
        venta.setDetallesVenta(listaDetallesVenta);
        venta.setTotal(total);

        Venta guardada = ventaRepository.save(venta);
        return VentaMapper.toResponseDto(guardada);
    }

    @Override
    public List<VentaResponseDto> listar() {
        return ventaRepository.findAll()
                .stream()
                .map(VentaMapper::toResponseDto)
                .toList();
    }

    @Override
    public VentaResponseDto buscarPorId(Long id) {
        return ventaRepository.findById(id)
                .map(VentaMapper::toResponseDto)
                .orElseThrow(() -> new RecursosNoEncontradoException(
                        "No se ha encontrado la venta con el di " + id
                ));
    }
}