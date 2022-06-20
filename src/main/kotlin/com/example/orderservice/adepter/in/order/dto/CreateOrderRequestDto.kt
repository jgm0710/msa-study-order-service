package com.example.orderservice.adepter.`in`.order.dto

import com.example.orderservice.application.command.CreateOrderCommand
import com.example.orderservice.domain.order.*
import java.util.*

/**
 *
 *
 * @author gm-jeong
 * @since 2022/06/19
 * */
data class CreateOrderRequestDto(
    val usedRewards: List<RewardRequestDto>,
    val ordererId: String
) {

    fun toCreateOrderCommand(): CreateOrderCommand {
        return CreateOrderCommand(
            usedRewards = usedRewards.map { it.toDomain() },
            ordererId = UUID.fromString(ordererId)
        )
    }

    data class RewardRequestDto(val rewardKind: RewardKind, val value: Int) {
        fun toDomain() : Reward{
            return when (rewardKind) {
                RewardKind.PASSORDER_POINT -> PassorderPoint(value)
                RewardKind.STORE_POINT -> StorePoint(value)
                RewardKind.STORE_STAMP -> StoreStamp(value)
            }
        }

    }

}