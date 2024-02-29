package ru.gb.homework9.cartservice.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.gb.homework9.cartservice.client.ExternalProductApi;
import ru.gb.homework9.cartservice.service.CartService;
import ru.gb.homework9.storeentity.entity.Cart;
import ru.gb.homework9.storeentity.entity.Product;
import ru.gb.homework9.storeutils.metrics.MyMetrics;


@Tag(name = "Cart API Controller", description = "Cart API Controller")
@RestController
@RequestMapping("/api/v1/carts")
@RequiredArgsConstructor
public class CartController {
    private final ExternalProductApi productApi;
    private final CartService cartService;
    private final MyMetrics myMetrics;

    @GetMapping("/about")
    public String about() {
        myMetrics.incrementAboutCounter();
        return "cartservice";
    }

    @PostMapping("/")
    public Cart addToCart(@RequestParam("cart_id") Long cart_id,
                          @RequestParam("product_id") Long product_id,
                          @RequestParam("quantity") int quantity) {
        Product product = productApi.findProductById(product_id);
        if (product != null && quantity > 0 && cart_id != null) {
            return cartService.addToCart(cart_id, product, quantity);
        }

        return null;
    }

    @DeleteMapping("/{id}")
    public void removeFromCart(@PathVariable("id") Long cartId,
                               @RequestParam("product_id") Long product_id) {
        Product product = productApi.findProductById(product_id);
        if (product != null && cartId != null) {
            cartService.removeFromCart(cartId, product);
        }
    }

    @PostMapping("/order/{id}")
    public String makeOrder(@PathVariable("id") Long cartId) {
        return cartService.placeOrder(cartId);
    }
}
