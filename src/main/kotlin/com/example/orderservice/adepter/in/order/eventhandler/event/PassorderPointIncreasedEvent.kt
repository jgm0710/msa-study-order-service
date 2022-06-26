package com.example.orderservice.adepter.`in`.order.eventhandler.event

import com.example.orderservice.application.command.CreateOrderSuccessCommand
import com.example.orderservice.common.DomainEvent
import com.fasterxml.jackson.databind.ObjectMapper
import java.util.*

/**
 *
 *
 * @author gm-jeong
 * @since 2022/06/26
 * */
data class PassorderPointIncreasedEvent(
    val id: String,
    val orderId: String,
    val totalPoint: Long,
    val pointHistory: Long,
    val createdAt: String
) : DomainEvent {
    fun toCreateOrderSuccessCommand(): CreateOrderSuccessCommand {
        return CreateOrderSuccessCommand(orderId = UUID.fromString(orderId))
    }

    companion object {
        fun ofJson(json: String) : PassorderPointIncreasedEvent{
            val objectMapper = ObjectMapper()

            val map = objectMapper.readValue(json, Map::class.java)

            return PassorderPointIncreasedEvent(
                id = map["id"] as String,
                orderId = map["orderId"] as String,
                totalPoint = (map["totalPoint"] as Int).toLong(),
                pointHistory = (map["pointHistory"] as Int).toLong(),
                createdAt = map["createdAt"] as String,
            )
        }
    }
}