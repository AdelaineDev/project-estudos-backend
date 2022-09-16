package com.project.dscatalog.controller;

import com.project.dscatalog.model.Categorias;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriasController {

    @GetMapping
    public ResponseEntity<List <Categorias>>findAll() {
        List<Categorias> list = new ArrayList<>();
        list.add(new Categorias(1L, "Livros"));
        list.add(new Categorias(1L, "Livros"));
        return ResponseEntity.ok().body(list);
    }


}
//ResponseEntity - Objeto que encapsula um metodo http (é um generic)