package ru.gb.homework9.productservice.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.homework9.productservice.database.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
