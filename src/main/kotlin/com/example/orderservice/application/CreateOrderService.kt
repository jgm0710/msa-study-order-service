package com.example.orderservice.application

import com.example.orderservice.application.command.CreateOrderCommand
import com.example.orderservice.application.command.CreateOrderSuccessCommand
import com.example.orderservice.application.exception.OrderNotFoundException
import com.example.orderservice.domain.order.Order
import com.example.orderservice.domain.order.OrderCreatedEventPublisher
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
    val orderCreatedEventPublisher: OrderCreatedEventPublisher
) {


    @Transactional
    fun createOrder(command: CreateOrderCommand): UUID {
        val resultWithDomainEvent = Order.createOrder(command.toCreateOrderEntityCommand())

        val order = resultWithDomainEvent.domain

        val savedOrder  = orderRepository.save(order)

        orderCreatedEventPublisher.publish(savedOrder::class.qualifiedName?:"", resultWithDomainEvent.event)

        return savedOrder.id
    }

    @Transactional
    fun createOrderSuccess(createOrderSuccessCommand: CreateOrderSuccessCommand) {
        val order = orderRepository.findByIdOrNull(createOrderSuccessCommand.orderId)
            ?: throw OrderNotFoundException("Approved order not found. order id : [${createOrderSuccessCommand.orderId}]")

        order.createOrderSuccess()

        orderRepository.save(order)
    }
}