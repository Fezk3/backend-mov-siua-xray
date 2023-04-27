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

    fun findByClassroomlistId(@Param("idClassroom") idClassroom: Long): List<Assets>

    @Query("SELECT a FROM Assets a WHERE a.classroomlist IN (SELECT c FROM Classroom c WHERE c.classNumber = :classNumber)")
    fun findByClassroomNumber(@Param("classNumber") classNumber: String): List<Assets>

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

    fun findByCollegeName(@Param("name") name : String) : List<Classroom>

}

@Repository
interface ScheduleRepository : JpaRepository<Schedule, Long> {
    fun findByUserId(@Param("userId") userId : Long) : List<Schedule>

}