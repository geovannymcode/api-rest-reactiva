package com.geovannycode.apirestcoroutine.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("app.school")
data class School(
    @Id val id: Long? = null,
    val name: String,
    val address: String,
    val email: String
)
