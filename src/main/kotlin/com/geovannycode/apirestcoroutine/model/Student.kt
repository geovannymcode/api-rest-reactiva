package com.geovannycode.apirestcoroutine.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("app.student")
data class Student(
    @Id val id: Long? = null,
    val firstName: String,
    val lastName: String,
    val email: String,
    val age: Int,
    val schoolId: Long
)