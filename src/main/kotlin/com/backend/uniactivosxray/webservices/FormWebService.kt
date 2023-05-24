package com.backend.uniactivosxray.webservices

import com.backend.uniactivosxray.FormDetails
import com.backend.uniactivosxray.services.FormService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("\${url.form}")

class FormController(private val formService: FormService) {

    @GetMapping
    @ResponseBody
    fun getAll(): List<FormDetails> {
        return formService.getAll()
    }

    @GetMapping("/{classroomId}")
    @ResponseBody
     fun getByClassroomId(@PathVariable classroomId: Long): List<FormDetails> {
        return formService.getByClassroomId(classroomId)
    }

}