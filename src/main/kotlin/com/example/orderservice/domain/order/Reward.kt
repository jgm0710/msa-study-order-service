package com.example.orderservice.domain.order

/**
 *
 *
 * @author gm-jeong
 * @since 2022/06/19
 * */
interface Reward {
    val rewardKind : RewardKind
    val value : Int
}