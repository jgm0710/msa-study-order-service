package com.example.orderservice.common

/**
 *
 *
 * @author gm-jeong
 * @since 2022/06/21
 * */
class ResultWithDomainEvent<T, R : DomainEvent>(val domain: T, val event: R){

}