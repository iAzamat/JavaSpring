package com.lesson12.block1.computerstore.domain;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Order {
    private int id = 0; // TODO
    private final List<Product> products = new CopyOnWriteArrayList<>();

    public Order(List<Product> prods) {
        id = 1;
        products.addAll(prods);
    }

    public List<Product> getProducts() {
        return Collections.unmodifiableList(products);
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    private static class OrderBuilder {
        private final List<Product> products = new CopyOnWriteArrayList<>();

        public OrderBuilder addProduct(Product product) {
            products.add(product);
            return this;
        }

        public Order build() {
            return new Order(products);
        }
    }
}
