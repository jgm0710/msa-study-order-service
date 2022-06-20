package com.example.orderservice.application

import com.example.orderservice.application.command.CreateOrderCommand
import com.example.orderservice.domain.order.CreateOrderEventPublisher
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
class CreateOrderService(
    val orderRepository: OrderRepository,
    val createOrderEventPublisher: CreateOrderEventPublisher
) {


    @Transactional
    fun createOrder(createOrderCommand: CreateOrderCommand): UUID {
        val order = Order.createOrder(createOrderCommand.toCreateOrderEntityCommand())

        val savedOrder  = orderRepository.save(order)

        createOrderEventPublisher.publish(savedOrder)

        return savedOrder.id
    }
}