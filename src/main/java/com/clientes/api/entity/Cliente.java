package com.clientes.api.entity;

import com.clientes.api.data.vo.ClienteVO;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
@Entity
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, length = 180)
    private String nome;
    
    @Column(name = "idade", nullable = false)
    private Integer idade;
    
    @Column(name = "cpf", nullable = false, unique = true)
    private String cpf;
    
    @Column(name = "data_nascimento", nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataNascimento;
    
     public static Cliente create(ClienteVO clienteVO) {
        return new ModelMapper().map(clienteVO,Cliente.class);
    }

}
