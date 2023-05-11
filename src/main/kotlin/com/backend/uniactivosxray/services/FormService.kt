package com.backend.uniactivosxray.services

import com.backend.uniactivosxray.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

interface FormService{
    fun getAll(): List<FormDetails>

    fun getByClassroomId(classroomId: Long): List<FormDetails>

}

@Service
class AbstractFormService(
        @Autowired
        val formRepository: FormRepository,
        @Autowired
        val formMapper: FormMapper,

        ) : FormService {

    override fun getAll(): List<FormDetails> {
        val formList: List<Form> = formRepository.findAll()
        return formMapper.formListToFormDetailsList(formList)
    }

    override fun getByClassroomId(classroomId: Long): List<FormDetails> {
        val formList: List<Form> = formRepository.findByClassroomId(classroomId)
        return formMapper.formListToFormDetailsList(formList)
    }

}