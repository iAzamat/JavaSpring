package ru.gb.homework9.cartservice.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
//@ToString(exclude = "products")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private Long customerId;

//    @ElementCollection
//    @CollectionTable(name = "cart_product_mapping",
//            joinColumns = @JoinColumn(name = "cart_id"))
//    @MapKeyJoinColumn(name = "product_id")
//    @Column(name = "quantity")
//    private Map<Product, Integer> products = new HashMap<>();

    private double totalPrice;
    private LocalDate createdAt;
    private LocalDate updatedAt;

//    public void addProduct(Product product, int quantity) {
//        products.put(product, quantity);
//    }
//
//    public void removeProduct(Product product) {
//        products.remove(product);
//    }
//
//    public int getProductQuantity(Product product) {
//        return products.getOrDefault(product, 0);
//    }
//
//    public void updateProductQuantity(Product product, int quantity) {
//        products.put(product, quantity);
//    }
}
