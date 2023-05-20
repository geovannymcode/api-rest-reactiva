package com.geovannycode.apirestcoroutine.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class StudentRequest(
    val email: String,
    val firstname: String,
    val lastname: String,
    val age: Int,
    @JsonProperty("school_id") val companyId: Long
)
