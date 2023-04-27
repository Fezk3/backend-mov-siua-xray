package com.backend.uniactivosxray

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.jdbc.Sql
import org.springframework.transaction.annotation.Transactional

@Transactional
@SpringBootTest
@Sql("/import_college.sql")
class CollegeRepositoryTest(
        @Autowired
        val collegeRepository: CollegeRepository,
) {

    @Test
    fun testCollegeFindAll() {
        val collegeList: List<College> = collegeRepository.findAll()
        Assertions.assertTrue(collegeList.size == 4)
    }

    @Test
    fun testCollegeFindByName() {
        val college: College = collegeRepository.findByName("College 1").get()
        Assertions.assertTrue(college.name == "College 1")
    }

}