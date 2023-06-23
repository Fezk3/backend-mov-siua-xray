package com.backend.uniactivosxray.webservices

import com.backend.uniactivosxray.*
import com.backend.uniactivosxray.services.FormHistoryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.crossstore.ChangeSetPersister
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("\${url.form-history}")
class FormHistoryController(private val formHistoryService: FormHistoryService,
    @Autowired
    val formHistoryRepository: FormHistoryRepository) {

    @GetMapping
    @ResponseBody
    fun getAll(): List<FormHistoryDetails> {
        return formHistoryService.getAll()
    }

    @GetMapping("/pendientes")
    @ResponseBody
    fun getPendientes(): List<FormHistory> {
        return formHistoryRepository.findPending()
    }

    @PostMapping
    fun create(@RequestBody formHistoryInput: FormHistoryInput): ResponseEntity<FormHistory> {
        println("formHistoryInput: $formHistoryInput")

        val formHistory = formHistoryService.create(formHistoryInput)

        println("formHistory: $formHistory")

        val formHistoryPendiendiente = formHistoryInput.copy(state = "Pendiente")
        formHistoryPendiendiente.formid = formHistoryInput.formid
        formHistoryService.create(formHistoryPendiendiente)
        return ResponseEntity.ok(formHistory)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long): ResponseEntity<FormHistory> {
        formHistoryRepository.updateFormHistory(id)
        return ResponseEntity.ok().build()
    }

}
