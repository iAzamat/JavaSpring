package com.lesson12.block1.computerstore.controller;

import com.lesson12.block1.computerstore.domain.Product;
import com.lesson12.block1.computerstore.service.OrderServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ComputerStoreControllerImpl implements ComputerStoreController {
    private OrderServiceFacade facade;

    @Autowired
    public ComputerStoreControllerImpl(OrderServiceFacade facade) {
        this.facade = facade;
    }

    @Override
    public ResponseEntity<Integer> orderProduct(int productId) {
        boolean status = facade.orderProduct(productId);

        // Logic
        return ResponseEntity.ok(1);
    }


}
