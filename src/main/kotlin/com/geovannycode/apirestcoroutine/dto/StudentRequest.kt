package com.geovannycode.apirestcoroutine.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class StudentRequest(
    val email: String,
    @JsonProperty("first_name") val firstName: String,
    @JsonProperty("last_name")val lastName: String,
    val age: Int,
    @JsonProperty("school_id") val schoolId: Long
)
