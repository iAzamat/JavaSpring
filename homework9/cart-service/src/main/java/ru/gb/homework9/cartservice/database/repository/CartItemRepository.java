package ru.gb.homework9.cartservice.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.gb.homework9.storeentity.entity.CartItem;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
