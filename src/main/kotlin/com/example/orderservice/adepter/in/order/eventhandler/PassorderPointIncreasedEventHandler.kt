package com.example.orderservice.adepter.`in`.order.eventhandler

import com.example.orderservice.adepter.`in`.order.eventhandler.event.PassorderPointIncreasedEvent
import com.example.orderservice.application.CreateOrderService
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

/**
 *
 *
 * @author gm-jeong
 * @since 2022/06/26
 * */
@Service
class PassorderPointIncreasedEventHandler(
    val createOrderService: CreateOrderService
) {

    val logger = LoggerFactory.getLogger(this::class.java)

    @KafkaListener(topics = ["com.example.msastudypointservice.domain.reward.PassorderPointIncreasedEvent"])
    fun consume(kafkaMessage : String) {
        logger.info("Passorder point increased event : [{}]", kafkaMessage)

        val passorderPointIncreasedEvent = PassorderPointIncreasedEvent.ofJson(kafkaMessage)

        createOrderService.createOrderSuccess(passorderPointIncreasedEvent.toCreateOrderSuccessCommand())

        logger.info("create order success!!")
    }
}