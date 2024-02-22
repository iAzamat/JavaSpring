package ru.gb.homework9.cartservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.gb.homework9.storeentity.entity.Product;

@FeignClient(name = "product")
public interface ExternalProductApi {
    @GetMapping("/{id}")
    Product findProductById(@PathVariable("id") Long id);
}
