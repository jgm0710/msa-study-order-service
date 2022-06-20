package com.example.orderservice.adepter.out.order

import com.example.orderservice.domain.order.CreateOrderEventPublisher
import com.example.orderservice.domain.order.Order
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

/**
 *
 *
 * @author gm-jeong
 * @since 2022/06/19
 * */
@Service
class KafkaCreateOrderEventPublisher(
    val kafkaTemplate: KafkaTemplate<String,String>
) : CreateOrderEventPublisher{

    val logger = LoggerFactory.getLogger(this::class.java)
    private final val topic = "create-order-topic"

    override fun publish(order: Order) {
        val objectMapper = ObjectMapper()

        val jsonData = objectMapper.registerModule(JavaTimeModule()).writeValueAsString(order)

        kafkaTemplate.send(topic, jsonData)
        logger.info("orderDto : [${jsonData}]")
    }
}