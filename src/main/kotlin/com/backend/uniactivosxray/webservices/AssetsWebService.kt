package com.backend.uniactivosxray.webservices

import com.backend.uniactivosxray.AssetsDetails
import com.backend.uniactivosxray.services.AssetService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("\${url.asset}")
class AssetController(private val assetService: AssetService) {

    @GetMapping
    @ResponseBody
    fun getAll(): List<AssetsDetails> {
        return assetService.getAll()
    }


    @GetMapping("/assets/{number}")
    @ResponseBody
    fun getByClassroomNumber(@PathVariable number: String): List<AssetsDetails> {
        return assetService.getByClassroomNumber(number)
    }

    @GetMapping("/dinamicos/{classNumber}")
    @ResponseBody
    fun getByAssetDinamicoAndClassroomId(@PathVariable classNumber:String): List<AssetsDetails> {
        return assetService.getByAssetDinamicoAndClassroomId(classNumber)
    }

    @GetMapping("/estaticos/{classNumber}")
    @ResponseBody
    fun getByAssetEstaticoAndClassroomId(@PathVariable classNumber:String): List<AssetsDetails> {
        return assetService.getByAssetEstaticoAndClassroomId(classNumber)
    }

}