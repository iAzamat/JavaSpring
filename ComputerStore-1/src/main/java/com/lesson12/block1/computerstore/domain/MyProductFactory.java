package com.lesson12.block1.computerstore.domain;

public class MyProductFactory implements ProductFactory {

    @Override
    public IProduct createProduct(int id) {
        Product.ProductBuilder builder = new Product.ProductBuilder();
        builder.color("red").customProp(1).id(id);

        return builder.build();
    }
}
