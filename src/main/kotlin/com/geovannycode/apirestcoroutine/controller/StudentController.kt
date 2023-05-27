package com.geovannycode.apirestcoroutine.controller

import com.geovannycode.apirestcoroutine.dto.StudentRequest
import com.geovannycode.apirestcoroutine.dto.StudentResponse
import com.geovannycode.apirestcoroutine.model.Student
import com.geovannycode.apirestcoroutine.service.StudentService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/students")
class StudentController(private val studentService: StudentService) {

    @GetMapping
    suspend fun findStudents(
        @RequestParam("name", required = false) name: String?,
    ): Flow<StudentResponse> {
        val students = name?.let { studentService.findAllStudentsByLastNameLike(name) }
            ?: studentService.findAllStudents()

        return students.map(Student::toResponse)
    }

    @PostMapping
    suspend fun createStudent(@RequestBody studentRequest: StudentRequest): StudentResponse =
        studentService.saveUser(
            student = studentRequest.toModel(),
        )
            ?.toResponse()
            ?: throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected error during student creation.")

    @GetMapping("/{id}")
    suspend fun findStudentById(
        @PathVariable id: Long,
    ): StudentResponse =
        studentService.findStudentById(id)
            ?.let(Student::toResponse)
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Student with id $id was not found.")

    @DeleteMapping("/{id}")
    suspend fun deleteStudentById(
        @PathVariable id: Long,
    ) {
        studentService.deleteStudentById(id)
    }

    @PutMapping("/{id}")
    suspend fun updateStudent(
        @PathVariable id: Long,
        @RequestBody studentRequest: StudentRequest,
    ): StudentResponse =
        studentService.updateStudent(
            id = id,
            requestedStudent = studentRequest.toModel(),
        )
            .toResponse()
}

fun Student.toResponse(): StudentResponse =
    StudentResponse(
        id = this.id!!,
        firstName = this.firstName,
        lastName = this.lastName,
        email = this.email,
        age = this.age,
    )

private fun StudentRequest.toModel(): Student =
    Student(
        email = this.email,
        firstName = this.firstName,
        lastName = this.lastName,
        age = this.age,
        schoolId = this.schoolId,
    )
