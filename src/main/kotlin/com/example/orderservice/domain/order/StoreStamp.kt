package com.example.orderservice.domain.order

/**
 *
 *
 * @author gm-jeong
 * @since 2022/06/19
 * */
class StoreStamp( override val value: Int) : Reward {
    override val rewardKind: RewardKind = RewardKind.STORE_STAMP
}