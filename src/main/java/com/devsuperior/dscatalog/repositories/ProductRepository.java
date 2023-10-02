package com.devsuperior.dscatalog.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.devsuperior.dscatalog.entities.Category;
import com.devsuperior.dscatalog.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

  @Query("SELECT DISTINCT obj FROM Product obj INNER JOIN obj.categories cats WHERE "
      + "(:category IS NULL OR cats = :category) AND "
      + "(LOWER(obj.name) LIKE LOWER(CONCAT('%',:name,'%')) )")
  Page<Product> find(@Param("category") Category category, String name, Pageable pageable);

}
