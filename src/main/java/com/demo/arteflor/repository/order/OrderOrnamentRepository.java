package com.demo.arteflor.repository.order;

import com.demo.arteflor.model.order.OrderOrnament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderOrnamentRepository extends JpaRepository<OrderOrnament, Integer> {
}
