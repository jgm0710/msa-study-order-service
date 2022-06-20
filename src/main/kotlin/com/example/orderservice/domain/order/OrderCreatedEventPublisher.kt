package com.example.orderservice.domain.order

import java.util.*

/**
 *
 *
 * @author gm-jeong
 * @since 2022/06/19
 * */
interface OrderCreatedEventPublisher {
    fun publish(aggregateType: String, orderId: UUID, orderCreatedEvent: OrderCreatedEvent)

}