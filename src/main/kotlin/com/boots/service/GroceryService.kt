package com.boots.service;

import com.boots.entity.Grocery
import com.boots.entity.User
import com.boots.repository.GroceryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Service
class GroceryService {
    @PersistenceContext
    private val em: EntityManager? = null

    @Autowired
    var groceryRepository: GroceryRepository? = null

    fun allItems(): List<Grocery> {
        return groceryRepository!!.findAll()
    }

    fun findItemById(itemId: Long): Grocery {
        val itemFromDb = groceryRepository!!.findById(itemId)
        return itemFromDb.orElse(Grocery())
    }
    fun saveItem(item: Grocery){
        groceryRepository!!.save(item)
    }
    fun deleteItem(itemId: Long): Boolean {
        if (groceryRepository!!.findById(itemId).isPresent) {
            groceryRepository!!.deleteById(itemId)
            return true
        }
        return false
    }
}