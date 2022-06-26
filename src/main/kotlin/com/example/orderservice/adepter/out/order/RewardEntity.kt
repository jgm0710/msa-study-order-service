package com.example.orderservice.adepter.out.order

import com.example.orderservice.domain.order.*
import java.util.*
import javax.persistence.*

/**
 *
 *
 * @author gm-jeong
 * @since 2022/06/20
 * */
@Entity
class RewardEntity(
    @Id
    @Column(name = "reward_id", nullable = false)
    var id: UUID,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    var orderEntity: OrderEntity?,
    @Enumerated(EnumType.STRING)
    val kind: RewardKind,
    val value: Int
) {


    fun toDomain(): Reward {
        return when (kind) {
            RewardKind.PASSORDER_POINT -> PassorderPoint(value)
            RewardKind.STORE_POINT -> StorePoint(value)
            RewardKind.STORE_STAMP -> StoreStamp(value)
        }
    }

    companion object {
        fun Reward.toEntity(): RewardEntity {
            return RewardEntity(
                UUID.randomUUID(),
                orderEntity = null,
                kind = rewardKind,
                value = value
            )
        }
    }

}