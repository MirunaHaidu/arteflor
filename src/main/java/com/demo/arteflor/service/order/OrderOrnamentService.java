package com.demo.arteflor.service.order;

import com.demo.arteflor.dto.order.OrderOrnamentDto;
import com.demo.arteflor.model.order.OrderOrnament;

public interface OrderOrnamentService {
    OrderOrnament addOrderedOrnament(OrderOrnamentDto orderOrnamentDto);
}
