package com.project.dscatalog.repository;

import com.project.dscatalog.model.Categorias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Categorias, Long> {
}
