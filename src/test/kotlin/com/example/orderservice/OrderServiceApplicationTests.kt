package com.example.orderservice

import com.example.orderservice.domain.order.Order
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.util.*

@SpringBootTest
class OrderServiceApplicationTests {

    @Test
    fun contextLoads() {
        println(UUID.randomUUID())

        println(Order::class.qualifiedName)
    }

}
