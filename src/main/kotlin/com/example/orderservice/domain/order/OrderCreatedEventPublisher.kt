package com.example.orderservice.domain.order

/**
 *
 *
 * @author gm-jeong
 * @since 2022/06/19
 * */
interface OrderCreatedEventPublisher {
    fun publish(aggregateType: String, orderCreatedEvent: OrderCreatedEvent)

}