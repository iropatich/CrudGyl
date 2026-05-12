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
        Cliente cliente = buscarClienteActivo(dto.idCliente());
        Venta venta = crearVenta(cliente);

        List<DetalleVenta> detalles = crearDetallesVenta(dto.detallesVenta(), venta);
        venta.setDetallesVenta(detalles);
        venta.setTotal(calcularTotal(detalles));

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


    private List<DetalleVenta> crearDetallesVenta(List<DetalleVentaRequestDto> listaDetallesDto, Venta venta) {
        List<DetalleVenta> detalles = new ArrayList<>();

        for (DetalleVentaRequestDto detalleDto: listaDetallesDto) {
            Producto producto = buscarProductoActivo(detalleDto.idProducto());
            ajustarStock(producto, detalleDto.cantidad());
            productoRepository.save(producto);

            DetalleVenta detalle = crearDetalleVenta(venta, producto, detalleDto.cantidad());
            detalles.add(detalle);
        }
        return detalles;
    }

    private Cliente buscarClienteActivo(Long id) {
        return clienteRepository.findByIdAndEstadoTrue(id)
                .orElseThrow(() -> new RecursosNoEncontradoException(
                        "No se ha encontrado el cliente con el id " + id
                ));
    }

    private Producto buscarProductoActivo(Long id) {
        return productoRepository.findByIdAndEstadoTrue(id)
                .orElseThrow(() -> new RecursosNoEncontradoException(
                        "No se ha encontrado el producto con el id " + id
                ));
    }

    private Venta crearVenta(Cliente cliente) {
        Venta venta = new Venta();
        venta.setFecha(LocalDateTime.now());
        venta.setCliente(cliente);

        return venta;
    }

    private DetalleVenta crearDetalleVenta(Venta venta, Producto producto, Integer cantidad) {
        double subtotal = producto.getPrecio() * cantidad;

        DetalleVenta detalleVenta = new DetalleVenta();
        detalleVenta.setCantidad(cantidad);
        detalleVenta.setPrecioUnitario(producto.getPrecio());
        detalleVenta.setSubtotal(subtotal);
        detalleVenta.setVenta(venta);
        detalleVenta.setProducto(producto);

        return detalleVenta;
    }

    private double calcularTotal(List<DetalleVenta> detalles) {
        return detalles.stream()
                .mapToDouble(DetalleVenta::getSubtotal)
                .sum();
    }

    private void ajustarStock(Producto producto, Integer cantidad) {
        if (producto.getStock() < cantidad) {
            throw new StockInsuficienteException(
                    "No hay suficiente stock del producto " + producto.getNombre()
            );
        }
        producto.setStock(producto.getStock() - cantidad);
    }
}