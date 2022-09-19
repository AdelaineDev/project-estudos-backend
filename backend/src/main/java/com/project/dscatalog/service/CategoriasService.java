package com.project.dscatalog.service;

import com.project.dscatalog.DTO.CategoriaDTO;
import com.project.dscatalog.model.Categorias;
import com.project.dscatalog.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriasService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<CategoriaDTO> findAll(){
        List<Categorias> list= categoryRepository.findAll();
        return list.stream().map(x -> new  CategoriaDTO(x)).collect(Collectors.toList());


    }
}
