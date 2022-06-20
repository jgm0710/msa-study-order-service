package com.example.orderservice.application.command

import com.example.orderservice.domain.order.CreateOrderEntityCommand
import com.example.orderservice.domain.order.Reward
import java.util.*

/**
 *
 *
 * @author gm-jeong
 * @since 2022/06/19
 * */
data class CreateOrderCommand(
    val usedReward: List<Reward>,
    val ordererId: UUID
) {

    fun toCreateOrderEntityCommand(): CreateOrderEntityCommand {
        return CreateOrderEntityCommand(
            usedReward = usedReward,
            ordererId = ordererId
        )
    }
}