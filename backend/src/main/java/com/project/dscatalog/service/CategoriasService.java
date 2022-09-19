package com.project.dscatalog.service;

import com.project.dscatalog.DTO.CategoriaDTO;
import com.project.dscatalog.service.exceptions.EntityNotFoundException;
import com.project.dscatalog.model.Categorias;
import com.project.dscatalog.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoriasService {
    @Autowired
    private CategoryRepository categoryRepository;
        @Transactional
    public List<CategoriaDTO> findAll(){
        List<Categorias> list= categoryRepository.findAll();
        return list.stream().map(x -> new  CategoriaDTO(x)).collect(Collectors.toList());


    }
    @Transactional
    public CategoriaDTO findById(Long id){
        Optional<Categorias> obj = categoryRepository.findById(id);
        Categorias entidade = obj.orElseThrow(()->new EntityNotFoundException("Categoria não encontrada"));
        return new CategoriaDTO(entidade);


    }


}
