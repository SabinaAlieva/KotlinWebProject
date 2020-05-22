package com.boots.service;

import com.boots.entity.Role
import com.boots.entity.User
import com.boots.repository.RoleRepository
import com.boots.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Service
class UserService : UserDetailsService {
    @PersistenceContext
    private val em: EntityManager? = null

    @Autowired
    var userRepository: UserRepository? = null

    @Autowired
    var roleRepository: RoleRepository? = null

    @Autowired
    var bCryptPasswordEncoder: BCryptPasswordEncoder? = null

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        return userRepository!!.findByUsername(username) ?: throw UsernameNotFoundException("User not found")
    }

    fun findUserById(userId: Long): User {
        val userFromDb = userRepository!!.findById(userId)
        return userFromDb.orElse(User())
    }

    fun allUsers(): List<User> {
        return userRepository!!.findAll()
    }

    fun saveUser(user: User): Boolean {
        val userFromDB = userRepository!!.findByUsername(user.username)
        if (userFromDB != null) {
            return false
        }
        user.roles = setOf(Role(1L, "ROLE_USER"))
        user.setPassword(bCryptPasswordEncoder!!.encode(user.password))
        userRepository!!.save(user)
        return true
    }

    fun deleteUser(userId: Long): Boolean {
        if (userRepository!!.findById(userId).isPresent) {
            userRepository!!.deleteById(userId)
            return true
        }
        return false
    }

    fun usergtList(idMin: Long?): List<User> {
        return em!!.createQuery("SELECT u FROM User u WHERE u.id > :paramId", User::class.java)
                .setParameter("paramId", idMin).resultList
    }
}