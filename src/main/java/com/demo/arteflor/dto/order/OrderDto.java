package com.demo.arteflor.dto.order;

import com.demo.arteflor.dto.user.PaymentDto;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String email;
//    private List<OrderOrnamentDto> orderOrnaments = new ArrayList<>();
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    private LocalDate orderDate;
    private Integer paymentId;


}
