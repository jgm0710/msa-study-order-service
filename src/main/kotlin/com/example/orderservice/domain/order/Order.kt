package com.example.orderservice.domain.order

import com.example.orderservice.common.ResultWithDomainEvent
import java.time.Instant
import java.util.*

/**
 *
 *
 * @author gm-jeong
 * @since 2022/06/19
 * */
class Order(
    val id : UUID,
    val ordererId : UUID,
    val usedRewards: List<Reward> = mutableListOf(),
    var status: OrderStatus,
    val createdAt: Instant
    ) {
    fun createOrderSuccess() {
        this.status = OrderStatus.WAITING_FOR_RECEPTION
    }


    companion object {
        fun createOrder(createOrderEntityCommand: CreateOrderEntityCommand): ResultWithDomainEvent<Order, OrderCreatedEvent> {
            val order = Order(
                id = UUID.randomUUID(),
                ordererId = createOrderEntityCommand.ordererId,
                usedRewards = createOrderEntityCommand.usedReward,
                status = OrderStatus.CREATED_PENDING,
                createdAt = Instant.now(),
            )
            return  ResultWithDomainEvent(order, OrderCreatedEvent(order))
        }
    }

}