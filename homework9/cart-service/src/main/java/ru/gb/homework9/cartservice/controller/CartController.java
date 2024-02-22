package ru.gb.homework9.cartservice.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.gb.homework9.cartservice.client.ExternalProductApi;
import ru.gb.homework9.cartservice.service.CartService;
import ru.gb.homework9.storeentity.entity.Cart;
import ru.gb.homework9.storeentity.entity.CartItem;
import ru.gb.homework9.storeentity.entity.Product;


@Tag(name = "Cart API Controller", description = "Cart API Controller")
@RestController
@RequestMapping("/api/v1/carts")
@RequiredArgsConstructor
public class CartController {

    // Добавление товаров в корзину, удаление товаров из корзины и оформление заказа.

    private final ExternalProductApi productApi;
    private final CartService cartService;

    @GetMapping("/about")
    public String about() {
        return "cartservice";
    }

    @GetMapping("/add")
    public Product addToCart() {
        Product product = productApi.findProductById(1L);
        return product;
    }

    @DeleteMapping("/remove/{productId}")
    public void removeFromCart(@PathVariable Long productId) {
        // Add logic to remove item from cart


    }

    @PostMapping("/checkout")
    public void checkout(@RequestBody Cart cart) {
        // Add logic to process checkout
    }
}
