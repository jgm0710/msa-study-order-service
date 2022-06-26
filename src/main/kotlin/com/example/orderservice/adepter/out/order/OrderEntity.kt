package com.example.orderservice.adepter.out.order

import com.example.orderservice.adepter.out.order.RewardEntity.Companion.toEntity
import com.example.orderservice.domain.order.Order
import com.example.orderservice.domain.order.OrderStatus
import java.time.Instant
import java.util.*
import javax.persistence.*

/**
 *
 *
 * @author gm-jeong
 * @since 2022/06/19
 * */
@Entity
class OrderEntity(
    @Id
    val id: UUID,
    val ordererId: UUID,
    @OneToMany(mappedBy = "orderEntity", cascade = [CascadeType.ALL])
    val usedRewards: MutableList<RewardEntity> = mutableListOf(),
    @Enumerated(EnumType.STRING)
    val status: OrderStatus,
    val createdAt: Instant
) {

    fun toDomain(): Order {
        return Order(
            id = id,
            ordererId = ordererId,
            usedRewards = usedRewards.map { it.toDomain() },
            status = status,
            createdAt = createdAt
        )
    }

    companion object {
        fun Order.toEntity(): OrderEntity {
            val orderEntity = OrderEntity(
                id = id,
                ordererId = ordererId,
                status = status,
                createdAt = createdAt
            )

            usedRewards.forEach{
                orderEntity.addReward(it.toEntity())
            }

            return orderEntity
        }


    }

    fun addReward(rewardEntity: RewardEntity) {
        usedRewards.add(rewardEntity)
        rewardEntity.orderEntity = this
    }

}