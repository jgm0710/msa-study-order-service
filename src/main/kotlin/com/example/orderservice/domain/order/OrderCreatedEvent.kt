package com.example.orderservice.domain.order

import com.example.orderservice.common.DomainEvent
import java.util.*

/**
 *
 *
 * @author gm-jeong
 * @since 2022/06/20
 * */
data class OrderCreatedEvent(
    val orderId  : UUID,
    val ordererId: UUID,
    val usedRewards: List<Reward> = mutableListOf(),
    val status: OrderStatus,
    val createdAt: String
) : DomainEvent {
    constructor(order: Order) : this(
        orderId = order.id,
        ordererId = order.ordererId,
        usedRewards = order.usedRewards,
        status = order.status,
        createdAt = order.createdAt.toString()
    )

}