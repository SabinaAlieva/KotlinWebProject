package com.boots.service;

import com.boots.entity.Grocery
import com.boots.entity.Order
import com.boots.entity.User
import com.boots.repository.OrderRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Service
class OrderService {
    @PersistenceContext
    private val em: EntityManager? = null

    @Autowired
    var orderRepository: OrderRepository? = null

    fun allItems(): List<Order> {
        return orderRepository!!.findAll()
    }

    fun buyItem(item: Grocery, user: User) {
        var order: Order? = Order(item = item, user = user)
        orderRepository!!.save(order)
    }
    fun findOrdersByUser(user: User): List<Order> {
        return orderRepository!!.findAllByUser(user)
    }
    fun deleteOrder(orderId: Long): Boolean {
        if (orderRepository!!.findById(orderId).isPresent) {
            orderRepository!!.deleteById(orderId)
            return true
        }
        return false
    }
}