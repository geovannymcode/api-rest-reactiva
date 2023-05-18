package com.geovannycode.apirestcoroutine.repository

import com.geovannycode.apirestcoroutine.model.Student
import kotlinx.coroutines.flow.Flow
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface StudentRepository : CoroutineCrudRepository<Student, Long> {

    fun findByFirstNameContaining(name: String): Flow<Student>
    fun findByLastNameContaining(name: String): Flow<Student>

    fun findBySchoolId(schoolId: Long): Flow<Student>
}