package com.boots.entity

import javax.persistence.*


@Entity
@Table(name = "t_grocery")
data class Grocery(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null,
        var category: String = "",
        var description: String = "",
        var price: Int = 0
)