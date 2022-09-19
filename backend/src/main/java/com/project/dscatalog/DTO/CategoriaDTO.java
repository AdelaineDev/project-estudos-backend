package com.project.dscatalog.DTO;

import com.project.dscatalog.model.Categorias;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class CategoriaDTO {
    private Long id;
    private String name;

    public CategoriaDTO(Categorias categorias) {
        this.id = categorias.getId();
        this.name = categorias.getName();
    }
}
