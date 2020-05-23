package com.boots.controller

import com.boots.entity.Grocery
import com.boots.service.GroceryService
import com.boots.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


@Controller
class AdminController {
    @Autowired
    private val userService: UserService? = null

    @Autowired
    private val groceryService: GroceryService? = null

    @GetMapping("/admin")
    fun userList(model: Model): String {
        model.addAttribute("allUsers", userService!!.allUsers())
        model.addAttribute("groceryForm", Grocery())
        return "admin"
    }

    @PostMapping("/admin")
    fun deleteUser(@RequestParam(required = true, defaultValue = "") userId: Long,
                   @RequestParam(required = true, defaultValue = "") action: String,
                   model: Model?): String {
        if (action == "delete") {
            userService!!.deleteUser(userId!!)
        } else if (action == "addRole") {
            userService!!.setRole(userId, "ROLE_MANAGER")
        } else if (action == "removeRole") {
            userService!!.removeRole(userId!!, "ROLE_MANAGER")
        }
        return "redirect:/admin"
    }

    @PostMapping("/admin_new_item")
    fun addNewComputer(@ModelAttribute("groceryForm") groceryForm: @Valid Grocery,
                       bindingResult: BindingResult, model: Model): String {
        groceryService!!.saveItem(groceryForm)
        return "redirect:/admin"
    }

    @PostMapping("/admin_change_item")
    fun changeComputer(id: Long, category: String, description: String, price: Int, model: Model): String {
        val item: Grocery = Grocery(id, category, description, price)
        groceryService!!.saveItem(item)
        return "redirect:/admin"
    }
}