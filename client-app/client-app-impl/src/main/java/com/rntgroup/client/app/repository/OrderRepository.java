package com.rntgroup.client.app.repository;

import com.rntgroup.client.app.entity.Order;
import com.rntgroup.client.app.enumerate.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Modifying
    @Query("UPDATE Order SET status = :status WHERE id = :id")
    void updateOrderStatus(@Param("id") Long id, @Param("status") OrderStatus orderStatus);

}
