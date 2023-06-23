package com.backend.uniactivosxray.services

import com.backend.uniactivosxray.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


interface FormHistoryService {
    fun getAll(): List<FormHistoryDetails>
    fun create(formHistoryInput: FormHistoryInput): FormHistory
    //fun create(formHistoryInput: FormHistoryInput): FormHistoryDetails?

}

@Service
class AbstractFormHistoryService(
    @Autowired
    val formHistoryRepository: FormHistoryRepository,
    @Autowired
    val formHistoryMapper: FormHistoryMapper,
    @Autowired
    val formRepository: FormRepository,
    @Autowired
    val userRepository: UserRepository,

    val state : String = "Entregado",
) : FormHistoryService {

    override fun getAll(): List<FormHistoryDetails> {
        val formHistoryList: List<FormHistory> = formHistoryRepository.findAll()
        return formHistoryMapper.formHistoryListToFormHistoryDetailsList(formHistoryList)
    }

    override fun create(formHistoryInput: FormHistoryInput): FormHistory {
        val formHistory = formHistoryMapper.formHistoryInputToFormHistory(formHistoryInput)
        return formHistoryRepository.save(formHistory)
    }

}