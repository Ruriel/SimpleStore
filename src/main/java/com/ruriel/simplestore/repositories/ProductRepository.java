package com.ruriel.simplestore.repositories;

import com.ruriel.simplestore.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findByEnabled(Boolean enabled, Pageable pageable);
}
