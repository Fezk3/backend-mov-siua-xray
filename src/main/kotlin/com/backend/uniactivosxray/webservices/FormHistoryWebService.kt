package com.backend.uniactivosxray.webservices

import com.backend.uniactivosxray.FormHistory
import com.backend.uniactivosxray.FormHistoryDetails
import com.backend.uniactivosxray.FormHistoryInput
import com.backend.uniactivosxray.services.FormHistoryService
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("\${url.form-history}")
class FormHistoryController(private val formHistoryService: FormHistoryService) {

    @GetMapping
    @ResponseBody
    fun getAll(): List<FormHistoryDetails> {
        return formHistoryService.getAll()
    }

    @PostMapping
    fun create(@RequestBody formHistoryInput: FormHistoryInput): ResponseEntity<FormHistory> {
        val formHistory = formHistoryService.create(formHistoryInput)
        return ResponseEntity.ok(formHistory)
    }

}