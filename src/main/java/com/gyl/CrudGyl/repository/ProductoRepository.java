package com.gyl.CrudGyl.repository;

import com.gyl.CrudGyl.entity.Producto;
import com.gyl.CrudGyl.entity.TipoProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
   List<Producto> findByNombreAndEstadoTrue(String nombre);

   List<Producto> findAllByEstadoTrue();

   Optional<Producto> findByIdAndEstadoTrue(Long id);

   boolean existsByTipoProductoAndEstadoTrue(TipoProducto tipoProducto);
}
