package com.example.orderservice.domain.order

import java.util.*

/**
 *
 *
 * @author gm-jeong
 * @since 2022/06/19
 * */
data class CreateOrderEntityCommand(
    val usedReward: List<Reward>,
    val ordererId: UUID
)