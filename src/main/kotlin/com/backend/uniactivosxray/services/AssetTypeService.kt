package com.backend.uniactivosxray.services

import com.backend.uniactivosxray.AssetType
import com.backend.uniactivosxray.AssetTypeDetails
import com.backend.uniactivosxray.AssetTypeMapper
import com.backend.uniactivosxray.AssetTypeRepository
import com.backend.uniactivosxray.AssetsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*


interface AssetTypeService {
    fun findAll(): List<AssetTypeDetails>
    fun getByDescription(description: String): List<AssetTypeDetails>
}

@Service
class AbstractAssetTypeService(

    @Autowired
    val assetTypeRepository: AssetTypeRepository,
    @Autowired
    val assetTypeMapper: AssetTypeMapper,
    @Autowired
    val assetsRepository: AssetsRepository,

    ) : AssetTypeService {
    override fun findAll(): List<AssetTypeDetails> {
        val assetTypeList: List<AssetType> = assetTypeRepository.findAll()
        return assetTypeMapper.assetTypeListToAssetTypeDetailsList(assetTypeList)
    }

    override fun getByDescription(description: String): List<AssetTypeDetails> {
        val assetTypeList: List<AssetType> = assetTypeRepository.findByDescription(description)
        return assetTypeMapper.assetTypeListToAssetTypeDetailsList(assetTypeList)
    }

}


