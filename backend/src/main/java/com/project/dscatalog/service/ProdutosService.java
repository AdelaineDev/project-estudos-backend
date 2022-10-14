package com.project.dscatalog.service;

import com.project.dscatalog.DTO.CategoriaDTO;
import com.project.dscatalog.DTO.produtosDTO;
import com.project.dscatalog.model.Categorias;
import com.project.dscatalog.model.Produtos;
import com.project.dscatalog.repository.CategoryRepository;
import com.project.dscatalog.repository.ProductRepository;
import com.project.dscatalog.service.exceptions.DatabaseException;
import com.project.dscatalog.service.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class ProdutosService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;


    @Transactional
    public Page<produtosDTO> findAllPaged(PageRequest pageRequest){
        Page<Produtos> list= productRepository.findAll(pageRequest);
        return list.map(produtosDTO::new);


    }
    @Transactional
    public produtosDTO findById(Long id){
        Optional<Produtos> obj = productRepository.findById(id);
        Produtos entidade = obj.orElseThrow(()-> new EntityNotFoundException("Produto não encontrada"));
        return new produtosDTO(entidade);


    }
    @Transactional
    public  produtosDTO insert(produtosDTO dto){
       Produtos produtos = new Produtos();
       copyDTOToModel(dto, produtos);
       produtos = productRepository.save(produtos);
        return new produtosDTO(produtos);
    }
    @Transactional
    public produtosDTO update(Long id, produtosDTO dto){
        try {
            Produtos produtos  = productRepository.getReferenceById(id);
            copyDTOToModel(dto, produtos);
           produtos  = productRepository.save(produtos);
            return new produtosDTO(produtos);

        }
        catch (EntityNotFoundException e){
            throw new EntityNotFoundException("Id não encontrado" + id);

        }


    }

    public void delete(Long id) throws DatabaseException {
        try {
            productRepository.deleteById(id);

        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Não encontrado" + id);

        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("integridade violada");
        }
    }

    private void copyDTOToModel(produtosDTO dto, Produtos entity){
        entity.setDate(dto.getDate());
        entity.setName(dto.getDescription());
        entity.setImgUrl(dto.getImgUrl());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());

        entity.getCategories().clear();
        for(CategoriaDTO catDTO: dto.getCategorias()){
            Categorias categorias = categoryRepository.getReferenceById(catDTO.getId());
            entity.getCategories().add(categorias);

        }

    }
}



