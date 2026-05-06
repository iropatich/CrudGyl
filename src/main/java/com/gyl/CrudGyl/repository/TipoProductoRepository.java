package com.gyl.CrudGyl.repository;

import com.gyl.CrudGyl.entity.TipoProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TipoProductoRepository extends JpaRepository<TipoProducto, Long> {
    List<TipoProducto> findByNombreAndEstadoTrue(String nombre);

    List<TipoProducto> findAllByEstadoTrue();

    Optional<TipoProducto> findByIdAndEstadoTrue(Long id);
}
