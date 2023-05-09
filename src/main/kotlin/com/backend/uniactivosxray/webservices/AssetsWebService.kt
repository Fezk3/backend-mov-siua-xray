package com.backend.uniactivosxray.webservices

import com.backend.uniactivosxray.AssetsDetails
import com.backend.uniactivosxray.services.AssetService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/assets")
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

    @GetMapping("/assets/{description}/{classroomId}")
    @ResponseBody
    fun getByAssetTypeAndClassroomId(@PathVariable description: String, @PathVariable classroomId: Long): List<AssetsDetails> {
        return assetService.getByAssetTypeAndClassroomId(description, classroomId)
    }

}