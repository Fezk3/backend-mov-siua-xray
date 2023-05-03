package com.backend.uniactivosxray

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.jdbc.Sql
import org.springframework.transaction.annotation.Transactional

@Transactional
@SpringBootTest
@Sql("/import_college.sql","/import_classroom.sql","/import_assetType.sql","/import_assets.sql")
class AssetsRepositoryTest(
    @Autowired
    val assetsRepository: AssetsRepository,
) {
    @Test
    fun testAssetsFindAll() {
        val assetsList: List<Assets> = assetsRepository.findAll()
        Assertions.assertTrue(assetsList.size == 4)
    }

    @Test
    fun testAssetsFindByCollegeName() {
        val assets: Assets = assetsRepository.findByName("Control del aire").get()
        Assertions.assertTrue(assets.name == "Control del aire")
    }

    @Test
    fun testAssetsFindByClassroom() {
        val assetsList: List<Assets> = assetsRepository.findByClassroom_Id(1)
        Assertions.assertTrue(assetsList.size == 2)
    }

    @Test
    fun testAssetsFindByClassroomNumber() {
        val assetsList: List<Assets> = assetsRepository.findByClassroomNumber("A-12")
        Assertions.assertTrue(assetsList.size == 2)
    }

}