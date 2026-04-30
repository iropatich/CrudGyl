package com.gyl.CrudGyl.service.interfaz;

import com.gyl.CrudGyl.dto.venta.VentaRequestDto;
import com.gyl.CrudGyl.dto.venta.VentaResponseDto;

import java.util.List;

public interface VentaService {
    VentaResponseDto crear(VentaRequestDto dto);

    List<VentaResponseDto> listar();

    VentaResponseDto buscarPorId(Long id);
}
