package ru.gb.homework9.cartservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.homework9.cartservice.database.repository.CartItemRepository;
import ru.gb.homework9.cartservice.database.repository.CartRepository;
import ru.gb.homework9.storeentity.entity.Cart;
import ru.gb.homework9.storeentity.entity.Product;
import ru.gb.homework9.storeutils.annotations.MyLog;
import ru.gb.homework9.storeutils.annotations.MyPerformance;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    /**
     * В этом методе, если корзина не найдена, то она будет создана,
     * но с другим идентификатором.
     * Здесь возможно привязать ид корзины к покупателю (один к одному)
     * Для этого необходимо создать ещё один микросервис для покупателей
     *
     * @param cartId   - ид корзины
     * @param product  - продукт
     * @param quantity - количество
     * @return корзину
     */
    @MyLog
    @MyPerformance
    public Cart addToCart(Long cartId, Product product, int quantity) {
        Cart cart = cartRepository.findById(cartId).orElse(new Cart());
        if (cart != null) {
            cart.addProduct(product, quantity);
            cartRepository.save(cart);
        }

        return cart;
    }

    @MyLog
    @MyPerformance
    public void removeFromCart(Long cartId, Product product) {
        Cart cart = cartRepository.findById(cartId).orElse(null);
        if (cart != null) {
            Long cartItemId = cart.removeProduct(product);
            cartRepository.save(cart);
            cartItemRepository.deleteById(cartItemId);
        }
    }

    /**
     * В этом методе выполнена заглушка в виде вывода суммы к оплате.
     * Этот метод должен передавать данные для другого микросервиса,
     * который занимается заказами и оплатой
     *
     * @param cartId ид корзины
     * @return текст с суммой к оплате
     */
    @MyLog
    @MyPerformance
    public String placeOrder(Long cartId) {
        Cart cart = cartRepository.findById(cartId).orElse(null);
        if (cart != null) {
            return "К оплате: " + cart.getTotalPrice();
        }
        return "Пустая корзина!";
    }
}
