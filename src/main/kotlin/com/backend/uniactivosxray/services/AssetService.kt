package com.backend.uniactivosxray.services

import com.backend.uniactivosxray.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

interface AssetService {
    fun getAll(): List<AssetsDetails>
    fun getByClassroomId(classroomId: Long): List<AssetsDetails>
    fun getByClassroomNumber(classroomNumber: String): List<AssetsDetails>
    fun getByAssetTypeAndClassroomId(description: String, classroomId: Long): List<AssetsDetails>
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

    override fun getAll(): List<AssetsDetails> {
        val assetsList: List<Assets> = assetsRepository.findAll()
        return assetsMapper.assetsListToAssetsDetailsList(assetsList)
    }

    override fun getByClassroomId(classroomId: Long): List<AssetsDetails> {
        val assetsList: List<Assets> = assetsRepository.findByClassroomId(classroomId)
        return assetsMapper.assetsListToAssetsDetailsList(assetsList)
    }

    override fun getByClassroomNumber(number: String): List<AssetsDetails> {
        val assetsList: List<Assets> = assetsRepository.findByClassroomNumber(number)
        return assetsMapper.assetsListToAssetsDetailsList(assetsList)
    }

    override fun getByAssetTypeAndClassroomId(description: String, classroomId: Long): List<AssetsDetails> {
        val assetsList: List<Assets> = assetsRepository.findByAssetTypeAndClassroomId(description, classroomId)
        return assetsMapper.assetsListToAssetsDetailsList(assetsList)
    }

}