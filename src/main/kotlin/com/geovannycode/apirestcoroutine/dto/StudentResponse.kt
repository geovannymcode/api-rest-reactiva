package com.geovannycode.apirestcoroutine.dto

data class StudentResponse(
    val id: Long,
    val email: String,
    val firstName: String,
    val lastName: String,
    val age: Int
)
