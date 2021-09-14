
package com.clientes.api.controller;

import com.clientes.api.data.vo.ClienteVO;
import com.clientes.api.entity.Cliente;
import com.clientes.api.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cliente")
public class ClienteController {
    
    private final ClienteService clienteService;
    private final PagedResourcesAssembler<ClienteVO> assembler;
    
    @Autowired
    public ClienteController(ClienteService clienteService, PagedResourcesAssembler<ClienteVO> assembler) {
        this.clienteService = clienteService;
        this.assembler = assembler;
    }
    
    
//    @Operation(summary = "Buscar por Id")
//    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Found the book",content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class))}),
//    @ApiResponse(responseCode = "400", description = "Invalid id supplied",content = @Content), 
//    @ApiResponse(responseCode = "404", description = "Book not found", content = @Content)})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Buscar Cliente por Id",content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class))})})
    @GetMapping(value = "buscar/porId/{id}", produces = {"application/json","application/xml","application/x-yaml"})
    public ClienteVO findById(@PathVariable("id") Long id) {
        ClienteVO clienteVO = clienteService.findById(id);
        clienteVO.add(link(id));
        return clienteVO;
    }
    
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Buscar Cliente por CPF",content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class))})})
    @GetMapping(value = "buscar/porCpf/{cpf}", produces = {"application/json","application/xml","application/x-yaml"})
    public ClienteVO findByCpf(@PathVariable("cpf") String cpf) {
        ClienteVO clienteVO = clienteService.findByCpf(cpf);
        clienteVO.add(link(clienteVO.getId()));
        return clienteVO;
    }
    
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Buscar Cliente por Nome",content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class))})})
    @GetMapping(value = "buscar/porNome/{nome}", produces = {"application/json","application/xml","application/x-yaml"})
    public ResponseEntity<?> findByNome(@PathVariable("nome") String nome, 
        @RequestParam(value = "page", defaultValue = "0") int page,
        @RequestParam(value = "limit", defaultValue = "10") int size,
        @RequestParam(value = "direction", defaultValue = "asc") String direction) {
        Sort.Direction dir = Sort.Direction.valueOf(direction.toUpperCase()) == Sort.Direction.DESC ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(dir,"nome"));
        Page<ClienteVO> produtos = clienteService.findByNome(nome,pageable);
        produtos.stream().forEach(p -> link(p.getId()));
        PagedModel<EntityModel<ClienteVO>> pageModel = assembler.toModel(produtos);
        return ResponseEntity.ok(pageModel);
    }
    
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Buscar Cliente por Idade",content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class))})})
    @GetMapping(value = "buscar/porIdade/{idade}", produces = {"application/json","application/xml","application/x-yaml"})
    public ResponseEntity<?> findByIdade(@PathVariable("idade") Integer idade, 
        @RequestParam(value = "page", defaultValue = "0") int page,
        @RequestParam(value = "limit", defaultValue = "10") int size,
        @RequestParam(value = "direction", defaultValue = "asc") String direction) {
        Sort.Direction dir = Sort.Direction.valueOf(direction.toUpperCase()) == Sort.Direction.DESC ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(dir,"idade"));
        Page<ClienteVO> produtos = clienteService.findByIdade(idade,pageable);
        produtos.stream().forEach(p -> link(p.getId()));
        PagedModel<EntityModel<ClienteVO>> pageModel = assembler.toModel(produtos);
        return ResponseEntity.ok(pageModel);
    }
    
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Buscar Cliente por Data nascimento",content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class))})})
    @GetMapping(value = "buscar/porDataNascimento/{dataNascimento}", produces = {"application/json","application/xml","application/x-yaml"})
    public ResponseEntity<?> findByIDataNascimento(@PathVariable("dataNascimento") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dataNascimento, 
        @RequestParam(value = "page", defaultValue = "0") int page,
        @RequestParam(value = "limit", defaultValue = "10") int size,
        @RequestParam(value = "direction", defaultValue = "asc") String direction) {
        Sort.Direction dir = Sort.Direction.valueOf(direction.toUpperCase()) == Sort.Direction.DESC ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(dir,"dataNascimento"));
        Page<ClienteVO> produtos = clienteService.findByDataNascimento(dataNascimento,pageable);
        produtos.stream().forEach(p -> link(p.getId()));
        PagedModel<EntityModel<ClienteVO>> pageModel = assembler.toModel(produtos);
        return ResponseEntity.ok(pageModel);
    }
    
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Buscar todos Clientes",content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class))})})
    @GetMapping(value = "todos", produces = {"application/json","application/xml","application/x-yaml"})
    public ResponseEntity<?> findAll(
        @RequestParam(value = "page", defaultValue = "0") int page,
        @RequestParam(value = "limit", defaultValue = "10") int size,
        @RequestParam(value = "direction", defaultValue = "asc") String direction) {
        
        Sort.Direction dir = Sort.Direction.valueOf(direction.toUpperCase()) == Sort.Direction.DESC ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(dir,"nome"));
        Page<ClienteVO> produtos = clienteService.findAll(pageable);
        produtos.stream().forEach(p -> link(p.getId()));
        PagedModel<EntityModel<ClienteVO>> pageModel = assembler.toModel(produtos);
        return ResponseEntity.ok(pageModel);
    }
    
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Salvar Cliente ",content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class))})})
    @PostMapping(consumes = {"application/json","application/xml","application/x-yaml"}, produces = {"application/json","application/xml","application/x-yaml"})
    public ResponseEntity<ClienteVO> create(@RequestBody ClienteVO produtoVO) {
        ClienteVO novo = clienteService.create(produtoVO);
        novo.add(link(novo.getId()));
        return new ResponseEntity<>(novo, HttpStatus.CREATED);
    }
    
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Alterar Cliente ",content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class))})})
    @PutMapping(consumes = {"application/json","application/xml","application/x-yaml"}, produces = {"application/json","application/xml","application/x-yaml"})
    public ResponseEntity<ClienteVO> update(@RequestBody ClienteVO produtoVO) {
        ClienteVO novo = clienteService.update(produtoVO);
        novo.add(link(novo.getId()));
        return new ResponseEntity<>(novo, HttpStatus.OK);
    }
    
    @ApiResponses(value = {@ApiResponse(responseCode = "204", description = "Deletar Cliente por Id",content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class))})})
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }
    
    private Link link (Long id) {
        return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ClienteController.class).findById(id)).withSelfRel();
    }
}
