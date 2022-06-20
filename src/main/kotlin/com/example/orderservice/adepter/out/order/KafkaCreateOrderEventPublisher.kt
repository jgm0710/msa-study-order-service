package com.example.orderservice.adepter.out.order

import com.example.orderservice.domain.order.OrderCreatedEventPublisher
import com.example.orderservice.domain.order.OrderCreatedEvent
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import java.util.*

/**
 *
 *
 * @author gm-jeong
 * @since 2022/06/19
 * */
@Service
class KafkaCreateOrderEventPublisher(
    val kafkaTemplate: KafkaTemplate<String,String>
) : OrderCreatedEventPublisher{

    val logger = LoggerFactory.getLogger(this::class.java)

    override fun publish(aggregateType: String, orderId : UUID, orderCreatedEvent: OrderCreatedEvent) {
        val objectMapper = ObjectMapper()

        val jsonData = objectMapper.registerModule(JavaTimeModule()).writeValueAsString(orderCreatedEvent)

        logger.info("publish order created event. orderCreatedEvent : [${jsonData}]")
        kafkaTemplate.send(aggregateType, orderId.toString(), jsonData)
    }
}