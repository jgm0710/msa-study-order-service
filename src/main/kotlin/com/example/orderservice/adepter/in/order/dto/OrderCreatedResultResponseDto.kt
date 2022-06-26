package com.example.orderservice.adepter.`in`.order.dto

import com.example.orderservice.domain.order.Order
import com.example.orderservice.domain.order.OrderStatus
import com.example.orderservice.domain.order.Reward
import java.time.Instant
import java.util.*

/**
 *
 *
 * @author gm-jeong
 * @since 2022/06/19
 * */
data class OrderCreatedResultResponseDto(
    val id : UUID,
    val ordererId : UUID,
    val usedRewards: List<Reward> = mutableListOf(),
    val status: OrderStatus,
    val createdAt: Instant
) {
    companion object {
        fun of(order: Order) : OrderCreatedResultResponseDto {
            return OrderCreatedResultResponseDto(
                id = order.id,
                ordererId = order.ordererId,
                usedRewards = order.usedRewards,
                status = order.status,
                createdAt = order.createdAt
            )
        }
    }
}