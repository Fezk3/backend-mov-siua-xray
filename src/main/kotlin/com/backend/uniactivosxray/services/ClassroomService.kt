package com.backend.uniactivosxray.services

import com.backend.uniactivosxray.Classroom
import com.backend.uniactivosxray.ClassroomDetails
import com.backend.uniactivosxray.ClassroomMapper
import com.backend.uniactivosxray.ClassroomRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


interface ClassroomService {

    fun getall(): List<ClassroomDetails>

    fun getbyclassnumber(number: String): ClassroomDetails

    fun getByCollegeName(collegeName: String): List<ClassroomDetails>

}

@Service
class ClassroomServiceImpl(

    @Autowired
    val classroomRepository: ClassroomRepository,
    @Autowired
    val classroomMapper: ClassroomMapper,

) : ClassroomService {

    override fun getall(): List<ClassroomDetails> {

        val classroomList: List<Classroom> = classroomRepository.findAll()
        return classroomMapper.classroomListToClassroomDetailsList(classroomList)

    }

    override fun getbyclassnumber(number: String): ClassroomDetails {

        val classroom: Classroom = classroomRepository.findByClassNumber(number).get()
        return classroomMapper.classroomToClassroomDetails(classroom)

    }

    override fun getByCollegeName(collegeName: String): List<ClassroomDetails> {

        val classroomList: List<Classroom> = classroomRepository.findByCollegeName(collegeName)
        return classroomMapper.classroomListToClassroomDetailsList(classroomList)

    }

}