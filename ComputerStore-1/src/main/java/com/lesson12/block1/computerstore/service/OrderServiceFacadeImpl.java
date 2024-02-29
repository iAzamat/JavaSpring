package com.lesson12.block1.computerstore.service;

import com.lesson12.block1.computerstore.domain.IProduct;
import com.lesson12.block1.computerstore.domain.MyProductFactory;
import com.lesson12.block1.computerstore.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceFacadeImpl implements OrderServiceFacade {

    @Autowired
    private MyProductFactory productFactory;

    @Override
    public boolean orderProduct(int productId) {
        boolean orderFulfilled = false;

        IProduct product = productFactory.createProduct(productId);

        if (InventoryService.isAvailable(product)) {
            System.out.println("Product with ID: "+ product.getProductId()+ " is available.");
            boolean paymentConfirmed= PaymentService.makePayment();

            if (paymentConfirmed){
                System.out.println("Payment confirmed...");
                ShippingService.shipProduct(product);
                System.out.println("Product shipped...");
                orderFulfilled = true;
            }
        }

        return orderFulfilled;
    }
}
