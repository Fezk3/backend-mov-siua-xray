package com.backend.uniactivosxray.webservices

import com.backend.uniactivosxray.CollegeDetails
import com.backend.uniactivosxray.services.CollegeService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("\${url.college}")
class CollegeController(private val collegeService: CollegeService) {

    @GetMapping
    @ResponseBody
    fun getAll(): List<CollegeDetails> {
        return collegeService.getall()
    }

    @GetMapping("/{name}")
    @ResponseBody
    fun getByName(@PathVariable name: String): CollegeDetails {
        return collegeService.getbyname(name)
    }

}