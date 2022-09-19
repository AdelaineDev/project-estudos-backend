package com.project.dscatalog.controller;

import com.project.dscatalog.DTO.CategoriaDTO;
import com.project.dscatalog.model.Categorias;
import com.project.dscatalog.service.CategoriasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
//ResponseEntity - Objeto que encapsula um metodo http (é um generic)