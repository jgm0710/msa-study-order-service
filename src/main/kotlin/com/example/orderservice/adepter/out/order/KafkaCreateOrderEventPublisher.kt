package com.example.orderservice.adepter.out.order

import com.example.orderservice.domain.order.OrderCreatedEvent
import com.example.orderservice.domain.order.OrderCreatedEventPublisher
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.SendResult
import org.springframework.stereotype.Service
import org.springframework.util.concurrent.ListenableFutureCallback

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

    override fun publish(aggregateType: String, orderCreatedEvent: OrderCreatedEvent) {
        val objectMapper = ObjectMapper()

        val jsonData = objectMapper.registerModule(JavaTimeModule()).writeValueAsString(orderCreatedEvent)

        logger.info("kafka topics = [$aggregateType]")
        logger.info("publish order created event. orderCreatedEvent : [${jsonData}]")

        kafkaTemplate.send(aggregateType, jsonData).addCallback(object : ListenableFutureCallback<SendResult<String,String>>{
            override fun onSuccess(result: SendResult<String, String>?) {
                logger.info(result.toString())
            }

            override fun onFailure(ex: Throwable) {
                logger.error(ex.message)
            }

        })
    }
}