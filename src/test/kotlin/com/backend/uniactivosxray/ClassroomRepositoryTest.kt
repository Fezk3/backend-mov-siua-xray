package com.backend.uniactivosxray

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.jdbc.Sql

@SpringBootTest
@Sql("/import_classroom.sql", "/import_college.sql", "/import_schedule.sql", "/import_users.sql", "/import_assets.sql", "/import_assetType.sql")
class ClassroomRepositoryTest(
    @Autowired
    val classroomRepository: ClassroomRepository,
) {
    @Test
    fun testClassroomFindAll() {
        val classroomList: List<Classroom> = classroomRepository.findAll()
        Assertions.assertTrue(classroomList.size == 2)
    }

    @Test
    fun testClassroomFindByCollegeName() {
        val classroomList: List<Classroom> = classroomRepository.findByCollegeName("College 1")
        Assertions.assertTrue(classroomList.size == 2)
    }
}
