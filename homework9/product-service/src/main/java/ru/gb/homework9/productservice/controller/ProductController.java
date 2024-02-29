package ru.gb.homework9.productservice.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.gb.homework9.productservice.service.ProductService;
import ru.gb.homework9.storeentity.entity.Product;
import ru.gb.homework9.storeutils.metrics.MyMetrics;

import java.util.List;

@Tag(name = "Product API Controller", description = "Product API Controller")
@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final MyMetrics myMetrics;

    @GetMapping("/about")
    public String about() {
        myMetrics.incrementAboutCounter();
        return "productservice";
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") Long id) {
        return productService.findById(id);
    }

    @PostMapping
    public Product addProduct(@RequestBody Product productTemp) {
        return productService.create(
                productTemp.getName(),
                productTemp.getDescription(),
                productTemp.getPrice());
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteById(id);
    }
}
