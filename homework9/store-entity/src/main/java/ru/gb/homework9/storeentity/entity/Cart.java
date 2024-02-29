package ru.gb.homework9.storeentity.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Data
@Entity
@Table(name = "cart", schema = "dbcart", catalog = "dbcart")
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "products")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double totalPrice;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<CartItem> products = new ArrayList<>();

    public void addProduct(Product product, int quantity) {
        for (CartItem item : products) {
            if (item.getProductId().equals(product.getId())) {
                item.setQuantity(item.getQuantity() + quantity);
                totalPrice += product.getPrice() * quantity;
                return;
            }
        }
        CartItem cartItem = new CartItem();
        cartItem.setProductId(product.getId());
        cartItem.setQuantity(quantity);
        cartItem.setCart(this);

        products.add(cartItem);

        totalPrice += product.getPrice() * quantity;
    }

    public Long removeProduct(Product product) {
        Iterator<CartItem> iterator = products.iterator();

        while (iterator.hasNext()) {
            CartItem item = iterator.next();

            if (item.getProductId().equals(product.getId())) {
                totalPrice -= item.getQuantity() * product.getPrice();
                iterator.remove();
                return item.getId();
            }
        }
        return null;
    }
}
