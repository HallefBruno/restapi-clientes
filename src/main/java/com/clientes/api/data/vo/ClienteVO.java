package com.clientes.api.data.vo;

import com.clientes.api.entity.Cliente;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonPropertyOrder({"id", "nome","idade", "cpf", "dataNascimento"})
public class ClienteVO extends RepresentationModel<ClienteVO> implements Serializable {

    @JsonProperty("id")
    private Long id;
    
    @JsonProperty("nome")
    private String nome;
    
    @JsonProperty("idade")
    private Integer idade;
    
    @JsonProperty("cpf")
    private String cpf;
    
    @JsonProperty("dataNascimento")
    private Date dataNascimento;
    
    public static ClienteVO create(Cliente cliente) {
        return new ModelMapper().map(cliente,ClienteVO.class);
    }

}
