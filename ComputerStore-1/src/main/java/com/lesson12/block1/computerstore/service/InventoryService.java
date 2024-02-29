package com.lesson12.block1.computerstore.service;

import com.lesson12.block1.computerstore.domain.IProduct;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {
    public static boolean isAvailable(IProduct product) {
        /*Check Warehouse database for product availability*/
        return true;
    }
}
