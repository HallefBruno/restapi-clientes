
package com.clientes.api.service;

import com.clientes.api.data.vo.ClienteVO;
import com.clientes.api.entity.Cliente;
import com.clientes.api.exception.ResourceNotFoundException;
import com.clientes.api.repository.ClienteRepository;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteService {
    
    private final ClienteRepository clienteRepository;
    
    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }
    
    @Transactional
    public ClienteVO create(ClienteVO clienteVO) {
        Cliente cliente = clienteRepository.save(Cliente.create(clienteVO));
        return ClienteVO.create(cliente);
    }
    
    public Page<ClienteVO> findAll(Pageable pageable) {
        Page<Cliente> page = clienteRepository.findAll(pageable);
        return page.map(this::convertToClienteVO);
    }
    
    private ClienteVO convertToClienteVO(Cliente produto) {
        return ClienteVO.create(produto);
    }
    
    public Page<ClienteVO> findByNome(String nome,Pageable pageable) {
        Page<Cliente> page = clienteRepository.findByNomeContainingIgnoreCase(nome,pageable);
        return page.map(this::convertToClienteVO);
    }
    
    public Page<ClienteVO> findByIdade(Integer idade,Pageable pageable) {
        Page<Cliente> page = clienteRepository.findByIdade(idade,pageable);
        return page.map(this::convertToClienteVO);
    }
    
    public Page<ClienteVO> findByDataNascimento(Date dataNascimento,Pageable pageable) {
        Page<Cliente> page = clienteRepository.findByDataNascimento(dataNascimento,pageable);
        return page.map(this::convertToClienteVO);
    }
    
    public ClienteVO findById(Long id) {
            
        Cliente produto = clienteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));
        return ClienteVO.create(produto);
    }

    public ClienteVO findByCpf(String cpf) {
        Cliente cliente = clienteRepository.findByCpf(cpf).orElseThrow(() -> new ResourceNotFoundException("No records found for this cpf"));
        return ClienteVO.create(cliente);
    }
    
    @Transactional
    public ClienteVO update(ClienteVO produtoVO) {
        final Optional<Cliente> opCliente = clienteRepository.findById(produtoVO.getId());
        if(!opCliente.isPresent())throw new ResourceNotFoundException("No records found for this id");
        return ClienteVO.create(clienteRepository.save(Cliente.create(produtoVO)));
    }
    
    @Transactional
    public void delete(Long id) {
        if(Objects.isNull(id)) {
            throw new ResourceNotFoundException("Id it can not be null"); 
        }
        Cliente produto = clienteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));
        clienteRepository.delete(produto);
    }
    
}
