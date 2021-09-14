
package com.clientes.api.repository;

import com.clientes.api.entity.Cliente;
import java.util.Date;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
    Page<Cliente> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
    Page<Cliente> findByIdade(Integer idade, Pageable pageable);
    Optional<Cliente> findByCpf(String cpf);
    Page<Cliente> findByDataNascimento(Date dataNascimento, Pageable pageable);
}
