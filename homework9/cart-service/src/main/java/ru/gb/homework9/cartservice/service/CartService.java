package ru.gb.homework9.cartservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.homework9.cartservice.database.repository.CartRepository;
import ru.gb.homework9.storeentity.entity.Cart;
import ru.gb.homework9.storeentity.entity.CartItem;
import ru.gb.homework9.storeutils.annotations.MyLog;
import ru.gb.homework9.storeutils.annotations.MyPerformance;

import java.util.Iterator;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {

    //Добавление товаров в корзину, удаление товаров из корзины и оформление заказа.

    private final CartRepository cartRepository;

    @MyLog
    @MyPerformance
    public void addToCart(Long cartId, Long productId, int quantity) {
        Cart cart = cartRepository.getReferenceById(cartId);

        CartItem cartItem = CartItem.builder()
                .cart(cart)
                .productId(productId)
                .quantity(quantity)
                .build();

        cart.getProducts().add(cartItem);
//        calculateTotalPrice(cart);
    }

    @MyLog
    @MyPerformance
    public void removeFromCart(Cart cart, Long productId) {
        List<CartItem> cartItems = cart.getProducts();

        for (Iterator<CartItem> iterator = cartItems.iterator(); iterator.hasNext(); ) {
            CartItem cartItem = iterator.next();

            if (cartItem.getProductId().equals(productId)) {
                iterator.remove();
                break;
            }
        }
    }

//    public double calculateTotalPrice(Cart cart, Long productId) {
//        double totalPrice = 0.0;
//
//        for (CartItem cartItem : cart.getProducts()) {
//            // Получить стоимость продукта и количество из элемента корзины
//            double productPrice = getProductPrice(cartItem.getProductId());
//            int quantity = cartItem.getQuantity();
//
//            // Подсчитать стоимость продукта в корзине
//            double itemTotalPrice = productPrice * quantity;
//
//            // Добавить стоимость продукта к общей стоимости корзины
//            totalPrice += itemTotalPrice;
//        }
//
//        return totalPrice;
//    }
}
