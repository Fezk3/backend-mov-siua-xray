package com.backend.uniactivosxray

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : JpaRepository<User, Long> {
    fun findByEmail(@Param("email") email : String) : Optional<User>
}

@Repository
interface RoleRepository : JpaRepository<Role, Long> {
    fun findByName (@Param("name") name : String) : Optional<Role>
}

@Repository
interface AssetsRepository : JpaRepository<Assets, Long> {
    fun findByName(@Param("name") name : String) : Optional<Assets>
}

@Repository
interface FormHistoryRepository : JpaRepository<FormHistory, Long> {
}

@Repository
interface CollegeRepository : JpaRepository<College, Long> {
    fun findByName(@Param("name") name : String) : Optional<College>
}

@Repository
interface ClassroomRepository : JpaRepository<Classroom, Long> {
    fun findByClassNumber (@Param("classNumber") classNumber : String) : Optional<Classroom>
}

@Repository
interface ScheduleRepository : JpaRepository<Schedule, Long> {
}