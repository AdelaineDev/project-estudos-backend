package com.project.dscatalog.controller;


import com.project.dscatalog.DTO.produtosDTO;
import com.project.dscatalog.service.ProdutosService;
import com.project.dscatalog.service.exceptions.DatabaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutosController {
    @Autowired
    public ProdutosService produtosService;

    @GetMapping
    public ResponseEntity<Page<produtosDTO>> findAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                     @RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
                                                     @RequestParam(value = "orderBy", defaultValue = "moment") String orderBy,
                                                     @RequestParam(value = "direction", defaultValue = "DESC") String direction)
    {
        PageRequest pageRequest = PageRequest.of(page,linesPerPage, Sort.Direction.valueOf(direction), orderBy);

        Page<produtosDTO> list = produtosService.findAllPaged(pageRequest);
        return ResponseEntity.ok().body(list);
    }
    @GetMapping(value = "{id}")
    public ResponseEntity <produtosDTO> findById(@PathVariable Long id) {
        produtosDTO dto = produtosService.findById(id);
        return ResponseEntity.ok().body(dto);

    }
    @PostMapping
    public ResponseEntity<produtosDTO> insert(@RequestBody produtosDTO dto){
        dto = produtosService.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);

    }
    @PutMapping (value = "{id}")
    public ResponseEntity<produtosDTO> update(@PathVariable Long id, @RequestBody produtosDTO dto){
        dto = produtosService.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);

    }
    @DeleteMapping (value = "/{id}")
    public ResponseEntity<produtosDTO> delete(@PathVariable Long id) throws DatabaseException {
        produtosService.delete(id);
        return ResponseEntity.noContent().build();

    }
}
