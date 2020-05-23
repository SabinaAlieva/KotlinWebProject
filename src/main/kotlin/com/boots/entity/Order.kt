package com.boots.entity

import javax.persistence.*


@Entity
@Table(name = "t_order")
data class Order(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null,

        @ManyToOne
        @JoinColumn(name="itemId")
        var item: Grocery? = null,

        @ManyToOne
        @JoinColumn(name="userId")
        var user: User? = null
)