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
data class OrderDetailResponseDto(
    val id : UUID,
    val ordererId : UUID,
    val usedReward: List<Reward>,
    val status: OrderStatus,
    val createdAt: Instant
) {

    companion object {
        fun of(order: Order)  : OrderDetailResponseDto{
            return OrderDetailResponseDto(
                id = order.id,
                ordererId = order.ordererId,
                usedReward = order.usedRewards,
                status = order.status,
                createdAt = order.createdAt,
            )
        }
    }
}