package com.backend.uniactivosxray.webservices

import com.backend.uniactivosxray.ClassroomDetails
import com.backend.uniactivosxray.services.ClassroomService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/classroom")
class ClassroomController(private val clasroomService: ClassroomService){

    @GetMapping
    @ResponseBody
    fun getAll(): List<ClassroomDetails> {
        return clasroomService.getAll()
    }

    @GetMapping("/classroom/{collegeName}")
    fun getByCollegeName(@PathVariable collegeName: String): List<ClassroomDetails> {
        return clasroomService.getByCollegeName(collegeName)
    }

    @GetMapping("/classroom/{classNumber}")
    fun getByClassNumber(@PathVariable classNumber: String): ClassroomDetails {
        return clasroomService.getByClassNumber(classNumber)
    }


}
