package com.backend.uniactivosxray

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.jdbc.Sql
import org.springframework.transaction.annotation.Transactional

@Transactional
@SpringBootTest
@Sql("/import_users.sql", "/import_college.sql", "/import_classroom.sql", "/import_schedule.sql")

class ScheduleRepositoryTest (
    @Autowired
    val scheduleRepository: ScheduleRepository,
){
    @Test
    fun testScheduleFindAll() {
        val scheduleList: List<Schedule> = scheduleRepository.findAll()
        Assertions.assertTrue(scheduleList.size == 5)
    }

    @Test
    fun testScheduleFindByUserId() {
        val scheduleList: List<Schedule> = scheduleRepository.findByUserId(4)
        Assertions.assertTrue(scheduleList.size == 2)
    }

    @Test
    fun testScheduleUpdate() {
        val scheduleUpdated: Schedule = scheduleRepository.findById(1).get()
        scheduleUpdated.courseName = "Discrete Mathematics"
        scheduleRepository.save(scheduleUpdated)
        assertEquals("Discrete Mathematics", scheduleRepository.findById(1).get().courseName)
    }
}