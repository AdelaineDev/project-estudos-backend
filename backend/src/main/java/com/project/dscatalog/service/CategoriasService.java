package com.project.dscatalog.service;

import com.project.dscatalog.model.Categorias;
import com.project.dscatalog.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriasService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Categorias> findAll(){
        return categoryRepository.findAll();
    }
}
