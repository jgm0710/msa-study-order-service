package com.example.orderservice.domain.order

import com.example.orderservice.common.DomainEvent
import java.time.Instant
import java.util.UUID

/**
 *
 *
 * @author gm-jeong
 * @since 2022/06/20
 * */
data class OrderCreatedEvent(
    val ordererId: UUID,
    val usedRewards: List<Reward> = mutableListOf(),
    val status: OrderStatus,
    val createdAt: Instant
) : DomainEvent {
    constructor(order: Order) : this(
        ordererId = order.ordererId,
        usedRewards = order.usedRewards,
        status = order.status,
        createdAt = order.createdAt
    )

}