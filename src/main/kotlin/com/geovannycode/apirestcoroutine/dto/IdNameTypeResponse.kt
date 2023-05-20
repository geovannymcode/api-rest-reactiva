package com.geovannycode.apirestcoroutine.dto

data class IdNameTypeResponse(
    val id: Long,
    val name: String,
    val type: ResultType
)

enum class ResultType {
    SCHOOL, STUDENT 
}
