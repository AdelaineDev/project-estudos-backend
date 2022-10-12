package com.project.dscatalog.repository;

import com.project.dscatalog.model.Produtos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Produtos, Long> {
}
