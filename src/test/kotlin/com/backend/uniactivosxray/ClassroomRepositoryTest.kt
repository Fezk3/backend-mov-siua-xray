package com.backend.uniactivosxray

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.jdbc.Sql
import org.springframework.transaction.annotation.Transactional

@Transactional
@SpringBootTest
@Sql("/import_college.sql", "/import_classroom.sql")
class ClassroomRepositoryTest(
    @Autowired
    val classroomRepository: ClassroomRepository,
) {
    @Test
    fun testClassroomFindAll() {
        val classroomList: List<Classroom> = classroomRepository.findAll()
        Assertions.assertTrue(classroomList.size == 3)
    }

    @Test
    fun testClassroomFindByCollegeName() {
        val classroomList: List<Classroom> = classroomRepository.findByCollegeName("College 1")
        Assertions.assertTrue(classroomList.size == 1)
    }

    @Test
    fun testClassroomFindByClassNumber() {
        val classroom: Classroom = classroomRepository.findByClassNumber("A-12").get()
        Assertions.assertTrue(classroom.classNumber == "A-12")
    }

}