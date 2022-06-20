package com.example.orderservice.domain.order

import java.util.*

/**
 *
 *
 * @author gm-jeong
 * @since 2022/06/19
 * */
interface OrderRepository {
    fun save(order: Order): Order
    fun findByIdOrNull(orderId: UUID) : Order?
}