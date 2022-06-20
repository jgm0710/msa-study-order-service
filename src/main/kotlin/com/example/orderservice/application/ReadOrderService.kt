package com.example.orderservice.application

import com.example.orderservice.application.exception.OrderNotFoundException
import com.example.orderservice.domain.order.Order
import com.example.orderservice.domain.order.OrderRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

/**
 *
 *
 * @author gm-jeong
 * @since 2022/06/19
 * */
@Service
@Transactional(readOnly = true)
class ReadOrderService(val orderRepository: OrderRepository) {

    fun getOrder(orderId : UUID): Order {
        return orderRepository.findByIdOrNull(orderId)
            ?: throw OrderNotFoundException("Order not found")
    }
}