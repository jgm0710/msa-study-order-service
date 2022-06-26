package com.example.orderservice.adepter.`in`.config

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory

/**
 *
 *
 * @author gm-jeong
 * @since 2022/06/21
 * */
@EnableKafka
@Configuration
class KafkaConsumerConfig {

    private val BROKER_CONNECTION = "127.0.0.1:9092"

    @Bean
    fun consumerFactory(): ConsumerFactory<String, String> {

        val properties = HashMap<String, Any>()
        properties[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = BROKER_CONNECTION
        properties[ConsumerConfig.GROUP_ID_CONFIG] = "consumerGroupId"
        properties[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.qualifiedName?:""
        properties[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.qualifiedName?:""

        return DefaultKafkaConsumerFactory(properties)
    }

    @Bean
    fun kafkaListenerContainerFactory(): ConcurrentKafkaListenerContainerFactory<String, String> {
        val kafkaListenerContainerFactory = ConcurrentKafkaListenerContainerFactory<String, String>()
        kafkaListenerContainerFactory.consumerFactory = consumerFactory()
        return kafkaListenerContainerFactory
    }
}