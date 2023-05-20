package com.geovannycode.apirestcoroutine.service

import com.geovannycode.apirestcoroutine.model.Student
import kotlinx.coroutines.flow.Flow

interface StudentService {
    suspend fun saveUser(student: Student): Student?
    suspend fun findAllStudents(): Flow<Student>
    suspend fun findStudentById(id: Long): Student?
    suspend fun deleteStudentById(id: Long)
    suspend fun updateStudent(id: Long, requestedStudent: Student): Student
    suspend fun findAllStudentsByLastNameLike(name: String): Flow<Student>
    suspend fun findStudentsBySchoolId(id: Long): Flow<Student>
}