package com.boots.repository


import com.boots.entity.Grocery
import com.boots.entity.User
import com.boots.entity.Order
import org.springframework.data.jpa.repository.JpaRepository


interface OrderRepository : JpaRepository<Order, Long> {
    fun findAllByUser(user: User?): List<Order>

    fun findAllByItem(item: Grocery?): Order?

}