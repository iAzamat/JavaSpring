package ru.gb.homework9.productservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.homework9.productservice.database.repository.ProductRepository;
import ru.gb.homework9.storeentity.entity.Product;
import ru.gb.homework9.storeutils.annotations.MyLog;
import ru.gb.homework9.storeutils.annotations.MyPerformance;

import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {
    private final ProductRepository productRepository;

    @MyLog
    @MyPerformance
    public Product create(String name, String description, double price) {
        Product product = new Product(name, description, price);
        productRepository.save(product);
        return product;
    }

    @MyLog
    @MyPerformance
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @MyLog
    @MyPerformance
    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @MyLog
    @MyPerformance
    public Long deleteById(Long id) {
        productRepository.findById(id)
                .ifPresent(product -> productRepository.deleteById(id));
        return id;
    }
}
