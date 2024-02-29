package com.lesson12.block1.computerstore.domain;

public class Product implements IProduct {

    public int productId;
    private String name;
    private String color;
    public int customProperty;

    public Product() {
        // Nothing to do
    }

    public Product(int productId, String name){
        this.productId = productId;
        this.name = name;
    }

    @Override
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getCustomProperty() {
        return customProperty;
    }

    public void setCustomProperty(int customProperty) {
        this.customProperty = customProperty;
    }

    public static class ProductBuilder {
        private final Product product = new Product();

        public ProductBuilder color(String color) {
            product.setColor(color);
            return this;
        }

        public ProductBuilder id(int prodId) {
            product.setProductId(prodId);
            return this;
        }

        public ProductBuilder customProp(int prop) {
            product.setCustomProperty(prop);
            return this;
        }

        public Product build() {
            return product;
        }
    }
}
