package com.geovannycode.apirestcoroutine.service

import com.geovannycode.apirestcoroutine.model.School
import com.geovannycode.apirestcoroutine.repository.SchoolRepository
import kotlinx.coroutines.flow.Flow
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class DefaultSchoolService(private val schoolRepository: SchoolRepository) : SchoolService {
    override suspend fun saveSchool(school: School): School? = schoolRepository.save(school)

    override suspend fun findAllSchools(): Flow<School> = schoolRepository.findAll()

    override suspend fun findSchoolById(id: Long): School? = schoolRepository.findById(id)

    override suspend fun deleteSchoolById(id: Long) {
        val foundSchool = schoolRepository.findById(id)

        if (foundSchool == null) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "School with id $id no found.")
        } else {
            schoolRepository.deleteById(id)
        }
    }

    override suspend fun findAllSchoolsByNameLike(name: String): Flow<School> = schoolRepository.findByNameContaining(name)

    override suspend fun updateSchool(id: Long, requestedSchool: School): School {
        val foundSchool = schoolRepository.findById(id)

        return if (foundSchool == null) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "School with id $id no found.")
        } else {
            schoolRepository.save(requestedSchool.copy(id = foundSchool.id))
        }
    }
}
