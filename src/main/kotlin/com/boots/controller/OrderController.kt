package com.boots.controller

import com.boots.service.GroceryService
import com.boots.entity.User
import com.boots.entity.Grocery
import com.boots.entity.Role
import com.boots.repository.RoleRepository
import com.boots.repository.UserRepository
import com.boots.service.OrderService
import com.boots.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import java.util.*


@Controller
class OrderController {
    @Autowired
    private val groceryService: GroceryService? = null

    @Autowired
    private val orderService: OrderService? = null

    @Autowired
    private val roleRepository: RoleRepository? = null

    @Autowired
    var userService: UserService? = null

    @GetMapping("/orders")
    fun orderList(model: Model): String {

        val user = SecurityContextHolder.getContext().authentication.principal as User
        val role = roleRepository!!.findByName("ROLE_MANAGER")
        var flag = 0
        for (rol in user.roles!!) {
            if (rol!!.name == role.name) {
                flag = 1
            }
        }
//                Role(3L, "ROLE_MANAGER")
        if (flag == 1) {
            model.addAttribute("allItems", orderService!!.allItems())
        } else {
            model.addAttribute("allItems", orderService!!.findOrdersByUser(user))
        }
        model.addAttribute("user", user)
        return "orders"
    }

    @PostMapping("/orders")
    fun deleteOrder(@RequestParam(required = true) userId: Long,
                    @RequestParam(required = true) orderId: Long,
                    model: Model?): String {
        val user = SecurityContextHolder.getContext().authentication.principal as User
        orderService!!.deleteOrder(orderId)
        return "redirect:/orders"
    }
}