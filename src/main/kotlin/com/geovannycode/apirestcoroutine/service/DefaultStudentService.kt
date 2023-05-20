package com.geovannycode.apirestcoroutine.service

import com.geovannycode.apirestcoroutine.model.Student
import com.geovannycode.apirestcoroutine.repository.StudentRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class DefaultStudentService(private val studentRepository: StudentRepository) : StudentService {
    override suspend fun saveUser(student: Student): Student? =
        studentRepository.randomNameFindByEmail(student.email)
            .firstOrNull()
            ?.let { throw ResponseStatusException(HttpStatus.BAD_REQUEST, "The specified email is already in student.") }
            ?: studentRepository.save(student)

    override suspend fun findAllStudents(): Flow<Student> = studentRepository.findAll()

    override suspend fun findStudentById(id: Long): Student? = studentRepository.findById(id)

    override suspend fun deleteStudentById(id: Long) {
        val foundStudent = studentRepository.findById(id)

        return if (foundStudent == null) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Stundet with id $id not found.")
        } else {
            studentRepository.deleteById(id)
        }
    }

    override suspend fun updateStudent(id: Long, requestedStudent: Student): Student {
        val foundStudent = studentRepository.findById(id)

        return if (foundStudent == null) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Stundet with id $id not found.")
        } else {
            studentRepository.save(requestedStudent.copy(id = foundStudent.id))
        }
    }

    override suspend fun findAllStudentsByLastNameLike(name: String): Flow<Student> =
        studentRepository.findByLastNameContaining(name)

    override suspend fun findStudentsBySchoolId(id: Long): Flow<Student> =
        studentRepository.findBySchoolId(id)
}
