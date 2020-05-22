package com.boots.repository

import com.boots.entity.Role
import org.springframework.data.jpa.repository.JpaRepository


interface RoleRepository : JpaRepository<Role, Long>
