package com.backend.uniactivosxray

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.jdbc.Sql
import org.springframework.transaction.annotation.Transactional

@Transactional
@SpringBootTest
@Sql("/import_college.sql","/import_classroom.sql","/import_assetType.sql")
class TypeassetRepositoryTest(
        @Autowired
        val assetsTypeRepository: AssetTypeRepository,
) {

    @Test
    fun testAssetsTypeFindAll() {
        val assetsTypeList: List<AssetType> = assetsTypeRepository.findAll()
        Assertions.assertTrue(assetsTypeList.size == 2)
    }

    @Test
    fun testAssetsTypeFindByName() {
        val assetsTypeList: List<AssetType> = assetsTypeRepository.findByDescription("Estatico")
        Assertions.assertTrue(assetsTypeList.size == 1)
    }

}