package com.example.orderservice.adepter.out.config

import org.apache.kafka.clients.producer.ProducerConfig
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory

/**
 *
 *
 * @author gm-jeong
 * @since 2022/06/19
 * */
@Configuration
class KafkaProducerConfiguration {

    private val BROKER_CONNECTION = "127.0.0.1:9092"

    @Bean
    fun producerFactory(): ProducerFactory<String, String> {
        val configurationProperties =  HashMap<String, Any>()
        configurationProperties[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = BROKER_CONNECTION
        configurationProperties[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = "org.apache.kafka.common.serialization.StringSerializer"
        configurationProperties[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = "org.apache.kafka.common.serialization.StringSerializer"
        return  DefaultKafkaProducerFactory(configurationProperties)
    }

    @Bean
    fun kafkaTemplate(): KafkaTemplate<String, String> {
        return KafkaTemplate(producerFactory())
    }

}