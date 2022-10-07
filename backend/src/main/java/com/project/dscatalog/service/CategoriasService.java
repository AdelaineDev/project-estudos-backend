package com.project.dscatalog.service;

import com.project.dscatalog.DTO.CategoriaDTO;
import com.project.dscatalog.service.exceptions.DatabaseException;
import com.project.dscatalog.service.exceptions.EntityNotFoundException;
import com.project.dscatalog.model.Categorias;
import com.project.dscatalog.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;

import java.util.Optional;

@Service
public class CategoriasService {
    @Autowired
    private CategoryRepository categoryRepository;
        @Transactional
        public Page<CategoriaDTO> findAllPaged(PageRequest pageRequest){
        Page<Categorias> list= categoryRepository.findAll(pageRequest);
        return list.map(CategoriaDTO::new);


    }
    @Transactional
    public CategoriaDTO findById(Long id){
        Optional<Categorias> obj = categoryRepository.findById(id);
        Categorias entidade = obj.orElseThrow(()-> new EntityNotFoundException("Categoria não encontrada"));
        return new CategoriaDTO(entidade);


    }
    @Transactional
    public  CategoriaDTO insert(CategoriaDTO dto){
            Categorias categorias = new Categorias();
            categorias.setName(dto.getName());
            categorias = categoryRepository.save(categorias);
            return new CategoriaDTO(categorias);
    }
    @Transactional
    public CategoriaDTO update(Long id, CategoriaDTO dto){
            try {
                Categorias categorias = categoryRepository.getReferenceById(id);
                categorias.setName(dto.getName());
                categorias = categoryRepository.save(categorias);
                return new CategoriaDTO(categorias);

            }
            catch (EntityNotFoundException e){
                throw new EntityNotFoundException("Id não encontrado" + id);

            }


    }

    public void delete(Long id) throws DatabaseException {
        try {
            categoryRepository.deleteById(id);

        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Não encontrado" + id);

        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("integridade violada");
        }
    }}


