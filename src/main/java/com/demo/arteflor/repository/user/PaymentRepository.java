package com.demo.arteflor.repository.user;

import com.demo.arteflor.model.user.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository <Payment, Integer> {
    Payment getById(Integer id);
}
