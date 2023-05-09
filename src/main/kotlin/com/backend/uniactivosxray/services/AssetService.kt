package com.backend.uniactivosxray.services

import com.backend.uniactivosxray.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*


interface AssetService {
    fun findAll(): List<AssetsDetails>?
    fun findByClassroomId(classroomId: Long): List<AssetsDetails>
    fun findByClassroomNumber(classroomNumber: String): List<AssetsDetails>
}

@Service
class AbstractAssetService(
    @Autowired
    val assetsRepository: AssetsRepository,
    @Autowired
    val assetsMapper: AssetsMapper,
    @Autowired
    val classroomRepository: ClassroomRepository,
    @Autowired
    val assetTypeRepository: AssetTypeRepository,
    ) : AssetService {

    override fun findAll(): List<AssetsDetails> {
        val assetsList: List<Assets> = assetsRepository.findAll().toList()
        return assetsMapper.assetsListToAssetsDetailsList(assetsList)
    }

    override fun findByClassroomId(classroomId: Long): List<AssetsDetails> {
        val assetsList: List<Assets> = assetsRepository.findByClassroomId(classroomId).toList()
        return assetsMapper.assetsListToAssetsDetailsList(assetsList)
    }

    override fun findByClassroomNumber(number: String): List<AssetsDetails> {
        val assetsList: List<Assets> = assetsRepository.findByClassroomNumber(number).toList()
        return assetsMapper.assetsListToAssetsDetailsList(assetsList)
    }

}