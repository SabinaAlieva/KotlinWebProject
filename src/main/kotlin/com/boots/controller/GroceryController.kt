package com.boots.controller

import com.boots.service.GroceryService
import com.boots.entity.User
import com.boots.entity.Grocery
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
class GroceryController {
    @Autowired
    private val groceryService: GroceryService? = null

    @Autowired
    private val orderService: OrderService? = null

    @Autowired
    var userService: UserService? = null

    @GetMapping("/grocery")
    fun itemList(model: Model): String {
        val user = SecurityContextHolder.getContext().authentication.principal

        model.addAttribute("allItems", groceryService!!.allItems())
        model.addAttribute("user", user)
        return "grocery"
    }

    @PostMapping("/grocery")
    fun buyItem(@RequestParam(required = true) userId: Long,
                @RequestParam(required = true) itemId: Long,
                model: Model?): String {
        val user = SecurityContextHolder.getContext().authentication.principal as User
        val item: Grocery = groceryService!!.findItemById(itemId)
        orderService!!.buyItem(item!!, user!!)
        return "redirect:/grocery"
    }
    @PostMapping("/grocery_delete")
    fun deleteItem(@RequestParam(required = true) itemId: Long,
                    model: Model?): String {
        groceryService!!.deleteItem(itemId)
        return "redirect:/grocery"
    }
}