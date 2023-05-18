package com.geovannycode.apirestcoroutine.repository

import com.geovannycode.apirestcoroutine.model.School
import kotlinx.coroutines.flow.Flow
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface SchoolRepository : CoroutineCrudRepository<School, Long> {
    fun findByNameContaining(name: String): Flow<School>
}