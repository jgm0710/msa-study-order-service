package com.example.orderservice.adepter.out.config

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
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
class KafkaConfiguration {

    private final val BROKER_CONNECTION = "127.0.0.1:9092"

    @Bean
    fun producerFactory(): ProducerFactory<String, String> {
        val configurationProperties =  HashMap<String, Any>()
        configurationProperties[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = BROKER_CONNECTION
        configurationProperties[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
        configurationProperties[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
        return  DefaultKafkaProducerFactory(configurationProperties)
    }

    @Bean
    fun kafkaTemplate(): KafkaTemplate<String, String> {
        return KafkaTemplate(producerFactory())
    }

    @Bean
    fun consumerFactory(): DefaultKafkaConsumerFactory<String, String> {
        val properties = HashMap<String, Any>()
        properties[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = BROKER_CONNECTION
        properties[ConsumerConfig.GROUP_ID_CONFIG] = "consumerGroupId"
        properties[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class
        properties[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class

        return DefaultKafkaConsumerFactory<String, String>(properties)
    }

    @Bean
    fun kafkaListenerContainerFactory(): ConcurrentKafkaListenerContainerFactory<String, String> {
        val kafkaListenerContainerFactory = ConcurrentKafkaListenerContainerFactory<String, String>()
        kafkaListenerContainerFactory.consumerFactory = consumerFactory()
        return kafkaListenerContainerFactory
    }
}