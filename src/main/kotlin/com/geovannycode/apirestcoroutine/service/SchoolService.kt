package com.geovannycode.apirestcoroutine.service

import com.geovannycode.apirestcoroutine.model.School
import kotlinx.coroutines.flow.Flow

interface SchoolService {
    suspend fun saveSchool(school: School): School?

    suspend fun findAllSchools(): Flow<School>

    suspend fun findSchoolById(id: Long): School?

    suspend fun deleteSchoolById(id: Long)

    suspend fun findAllSchoolsByNameLike(name: String): Flow<School>

    suspend fun updateSchool(id: Long, requestedSchool: School): School
}