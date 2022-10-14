package com.project.dscatalog.DTO;

import com.project.dscatalog.model.Categorias;
import com.project.dscatalog.model.Produtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
public class produtosDTO implements Serializable {
    private static long serialVersionId = 1l;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    private String description ;
    private Double price;
    private String imgUrl;
    private Instant date;

    private List<CategoriaDTO> categorias = new ArrayList<>();

    public produtosDTO(Long id, String name, String description, Double price, String imgUrl, Instant date) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
        this.date = date;
    }
    public produtosDTO(Produtos entity){
        this.id = entity.getId();
        this.name = entity.getName();
        this.description = entity.getDescription();
        this.price = entity.getPrice();
        this.imgUrl = entity.getImgUrl();
        this.date = entity.getDate();

    }
    public produtosDTO(Produtos entity, Set<Categorias> categorias){
        this (entity);
        categorias.forEach(cat -> this.categorias.add(new CategoriaDTO(cat)));
}


    public produtosDTO(produtosDTO produtos) {
    }
}
