package com.example.orderservice.adepter.out.order

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

/**
 *
 *
 * @author gm-jeong
 * @since 2022/06/19
 * */
interface SpringDataJpaOrderRepository: JpaRepository<OrderEntity, UUID> {
}