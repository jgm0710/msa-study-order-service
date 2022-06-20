package com.example.orderservice.domain.order

/**
 *
 *
 * @author gm-jeong
 * @since 2022/06/19
 * */
enum class OrderStatus {
    CREATED_PENDING,
    WAITING_FOR_RECEPTION,
    REJECTED
}