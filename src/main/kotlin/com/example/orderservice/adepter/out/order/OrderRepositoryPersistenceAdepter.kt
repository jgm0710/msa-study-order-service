package com.example.orderservice.adepter.out.order

import com.example.orderservice.adepter.out.order.OrderEntity.Companion.toEntity
import com.example.orderservice.domain.order.Order
import com.example.orderservice.domain.order.OrderRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import java.util.*

/**
 *
 *
 * @author gm-jeong
 * @since 2022/06/19
 * */
@Repository
class OrderRepositoryPersistenceAdepter(
    val springDataJpaOrderRepository: SpringDataJpaOrderRepository
): OrderRepository {
    override fun save(order: Order): Order {
        return springDataJpaOrderRepository.save(order.toEntity()).toDomain()
    }

    override fun findByIdOrNull(orderId: UUID): Order? {
        return springDataJpaOrderRepository.findByIdOrNull(orderId)?.toDomain()
    }
}