package com.gyl.CrudGyl.service.impl;

import com.gyl.CrudGyl.dto.cliente.ClienteRequestDto;
import com.gyl.CrudGyl.dto.cliente.ClienteResponseDto;
import com.gyl.CrudGyl.entity.Cliente;
import com.gyl.CrudGyl.exception.EmailExistenteException;
import com.gyl.CrudGyl.exception.RecursoYaActivoException;
import com.gyl.CrudGyl.exception.RecursosNoEncontradoException;
import com.gyl.CrudGyl.mapper.ClienteMapper;
import com.gyl.CrudGyl.repository.ClienteRepository;
import com.gyl.CrudGyl.service.interfaz.ClienteService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {
    private ClienteRepository clienteRepository;

    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public ClienteResponseDto crear(ClienteRequestDto dto) {
        if (clienteRepository.existsByEmail(dto.email())) {
            throw new EmailExistenteException(
                    "Ya existe un cliente con el mail " + dto.email()
            );
        }
        Cliente cliente = ClienteMapper.toEntity(dto);
        Cliente guardado = clienteRepository.save(cliente);
        return ClienteMapper.toResponseDto(guardado);
    }

    @Override
    public List<ClienteResponseDto> listar() {
        return clienteRepository.findAllByEstadoTrue()
                .stream()
                .map(ClienteMapper::toResponseDto)
                .toList();
    }

    @Override
    public ClienteResponseDto buscarPorId(Long id) {
        return clienteRepository.findByIdAndEstadoTrue(id)
                .map(ClienteMapper::toResponseDto)
                .orElseThrow(() -> new RecursosNoEncontradoException(
                        "No se encontroe el cliente con el id " + id
                ));
    }

    @Override
    public List<ClienteResponseDto> buscarNombre(String nombre) {
        List<ClienteResponseDto> clientes = clienteRepository.findByNombreAndEstadoTrue(nombre)
                .stream()
                .map(ClienteMapper::toResponseDto)
                .toList();

        if (clientes.isEmpty()) {
            throw new RecursosNoEncontradoException(
                    "No se encontraron clientes con el nombre " + nombre
            );
        }
        return clientes;
    }

    @Override
    public ClienteResponseDto actualizar(Long id, ClienteRequestDto dto) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RecursosNoEncontradoException(
                        "No se encontro el cliente con el id " + id
                ));
        if (!cliente.getEmail().equals(dto.email()) && clienteRepository.existsByEmail(dto.email())) {
            throw new EmailExistenteException(
                    "Ya existe un cliente con el mail " + dto.email()
            );
        }
        ClienteMapper.updateEntity(cliente, dto);
        Cliente guardado = clienteRepository.save(cliente);
        return ClienteMapper.toResponseDto(guardado);
    }

    @Override
    public void eliminar(Long id) {
        Cliente cliente = clienteRepository.findByIdAndEstadoTrue(id)
                .orElseThrow(() -> new RecursosNoEncontradoException(
                        "No se encontro el cliente con el id " + id
                ));
        cliente.setEstado(false);
        clienteRepository.save(cliente);
    }

    @Override
    public void restaurar(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RecursosNoEncontradoException(
                        "No se encontro el cliente con el id " + id
                ));
        if (cliente.getEstado()) {
            throw new RecursoYaActivoException(
                    "El cliente ya se encuentra activo"
            );
        }
        cliente.setEstado(true);
        clienteRepository.save(cliente);
    }
}
