package com.geovannycode.apirestcoroutine.controller

import com.geovannycode.apirestcoroutine.dto.StudentResponse
import com.geovannycode.apirestcoroutine.model.Student
import com.geovannycode.apirestcoroutine.service.StudentService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/students")
class StudentController(private val studentService: StudentService){

    @GetMapping
    suspend fun findStudents(
        @RequestParam("name", required = false) name: String?
    ): Flow<StudentResponse> {
        val students = name?.let { studentService.findAllStudentsByLastNameLike(name) }
            ?: studentService.findAllStudents()

        return students.map(Student::toResponse)
    }
}

fun Student.toResponse(): StudentResponse =
    StudentResponse(
        id = this.id!!,
        firstName = this.firstName,
        lastName = this.lastName,
        email = this.email,
        age = this.age,
    )
