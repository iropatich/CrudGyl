package com.gyl.CrudGyl.repository;

import com.gyl.CrudGyl.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    List<Cliente> findByNombreAndEstadoTrue(String nombre);

    boolean existsByEmail(String email);

    List<Cliente> findAllByEstadoTrue();

    Optional<Cliente> findByIdAndEstadoTrue(Long id);
}
