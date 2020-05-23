package com.boots.repository

import com.boots.entity.Grocery
import org.springframework.data.jpa.repository.JpaRepository


interface GroceryRepository : JpaRepository<Grocery, Long> {
    fun findByDescription(description: String?): Grocery?

}