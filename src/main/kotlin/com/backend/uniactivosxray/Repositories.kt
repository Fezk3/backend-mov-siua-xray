package com.backend.uniactivosxray

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
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

    fun findByClassroomId(@Param("classroomId") classroomId: Long): List<Assets>

    @Query("SELECT a FROM Assets a WHERE a.assetType.description = :description AND a.classroom.id = :classroomId")
    fun findByAssetTypeAndClassroomId(@Param("description") description: String, @Param("classroomId") classroomId: Long): List<Assets>

    @Query("SELECT a FROM Assets a WHERE a.classroom IN (SELECT c FROM Classroom c WHERE c.classNumber = :classNumber)")
    fun findByClassroomNumber(@Param("classNumber") classNumber: String): List<Assets>


}

@Repository
interface FormHistoryRepository : JpaRepository<FormHistory, Long> {

    @Query("SELECT f FROM FormHistory f WHERE f.state = 'Pendiente'")
    fun findPending(): List<FormHistory>

    // metodo que en base a un id cambie el estado del formHistory a Recibido y el user_id a 2
    @Modifying
    @Transactional
    @Query("UPDATE FormHistory f SET f.state = 'Recibido', f.userId = 2 WHERE f.id = :id")
    fun updateFormHistory(@Param("id") id: Long)


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
interface AssetTypeRepository : JpaRepository<AssetType, Long> {
    fun findByDescription(@Param("description") description : String) : List<AssetType>
}

@Repository
interface ScheduleRepository : JpaRepository<Schedule, Long> {
    fun findByUserId(@Param("userId") userId : Long) : List<Schedule>
    fun findByClassroomId(@Param("classroomId") classroomId : Long) : List<Schedule>
}

@Repository
interface PrivilegeRepository : JpaRepository<Privilege, Long> {
    fun findByName(@Param("name") name : String) : Optional<Privilege>
}