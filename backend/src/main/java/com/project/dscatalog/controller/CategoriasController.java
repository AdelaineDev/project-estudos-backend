package com.project.dscatalog.controller;

import com.project.dscatalog.DTO.CategoriaDTO;
import com.project.dscatalog.model.Categorias;
import com.project.dscatalog.service.CategoriasService;
import com.project.dscatalog.service.exceptions.DatabaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriasController {
    @Autowired
    public CategoriasService categoriasService;

    @GetMapping
    public ResponseEntity<List <CategoriaDTO>> findAll() {
        List<CategoriaDTO> list = categoriasService.findAll();

        return ResponseEntity.ok().body(list);
    }
    @GetMapping(value = "{id}")
    public ResponseEntity <CategoriaDTO> findById(@PathVariable Long id) {
        CategoriaDTO dto = categoriasService.findById(id);
        return ResponseEntity.ok().body(dto);

}
@PostMapping
    public ResponseEntity<CategoriaDTO> insert(@RequestBody CategoriaDTO dto){
        dto = categoriasService.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);

}
@PutMapping (value = "{id}")
public ResponseEntity<CategoriaDTO> update(@PathVariable Long id, @RequestBody CategoriaDTO dto){
    dto = categoriasService.insert(dto);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
    return ResponseEntity.created(uri).body(dto);

}
    @DeleteMapping (value = "/{id}")
    public ResponseEntity<CategoriaDTO> delete(@PathVariable Long id) throws DatabaseException {
       categoriasService.delete(id);
        return ResponseEntity.noContent().build();

    }
}
//ResponseEntity - Objeto que encapsula um metodo http (é um generic)