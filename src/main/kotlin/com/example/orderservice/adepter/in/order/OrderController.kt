package com.example.orderservice.adepter.`in`.order

import com.example.orderservice.adepter.`in`.order.dto.CreateOrderRequestDto
import com.example.orderservice.adepter.`in`.order.dto.OrderCreatedResultResponseDto
import com.example.orderservice.adepter.`in`.order.dto.OrderDetailResponseDto
import com.example.orderservice.application.CreateOrderService
import com.example.orderservice.application.ReadOrderService
import com.example.orderservice.common.ErrorsThrowUtil.Companion.hasErrorsThrow
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid

/**
 *
 *
 * @author gm-jeong
 * @since 2022/06/19
 * */
@RestController
@RequestMapping("/orders")
class OrderController(val createOrderService: CreateOrderService, val readOrderService: ReadOrderService) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createOrder(
        @RequestBody @Valid createOrderRequestDto: CreateOrderRequestDto,
        errors: Errors
    ): OrderCreatedResultResponseDto {

        logger.info("create order request !! ")
        hasErrorsThrow(errors)

        val createdId = createOrderService.createOrder(createOrderRequestDto.toCreateOrderCommand())
        val order = readOrderService.getOrder(createdId)

        return OrderCreatedResultResponseDto.of(order)
    }

    @GetMapping("/{orderId}")
    @ResponseStatus(HttpStatus.OK)
    fun getOrderDetail(@PathVariable orderId: UUID): OrderDetailResponseDto {
        val findOrder = readOrderService.getOrder(orderId)
        return OrderDetailResponseDto.of(findOrder)
    }

    @GetMapping("/health-check")
    @ResponseStatus(HttpStatus.OK)
    fun healthCheck(): String {
        return "Order service is running."
    }
}