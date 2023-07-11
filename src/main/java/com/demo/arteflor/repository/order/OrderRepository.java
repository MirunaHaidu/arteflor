package com.demo.arteflor.repository.order;

import com.demo.arteflor.model.order.Order;
import com.demo.arteflor.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    Order getById(Integer id);

}
