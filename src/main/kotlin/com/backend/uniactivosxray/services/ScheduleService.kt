package com.backend.uniactivosxray.services

import com.backend.uniactivosxray.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


interface ScheduleService {
    fun getAll() : List<ScheduleDetails>
    fun getByUserId(userId : Long) : List<ScheduleDetails>
    fun getByClassroomId(classroomId : Long) : List<ScheduleDetails>
}

@Service
class AbstractScheduleService(
    @Autowired
    val scheduleRepository: ScheduleRepository,
    @Autowired
    val scheduleMapper: ScheduleMapper,
    @Autowired
    val classroomRepository: ClassroomRepository,
    @Autowired
    val userRepository: UserRepository,
) : ScheduleService {

    override fun getAll() : List<ScheduleDetails> {
        val scheduleList: List<Schedule> = scheduleRepository.findAll()
        return scheduleMapper.scheduleListToScheduleDetailsList(scheduleList)
    }

    override fun getByUserId(userId: Long) : List<ScheduleDetails> {
        val scheduleList: List<Schedule> = scheduleRepository.findByUserId(userId)
        return scheduleMapper.scheduleListToScheduleDetailsList(scheduleList)
    }

    override fun getByClassroomId(classroomId : Long) : List<ScheduleDetails> {
        val scheduleList: List<Schedule> = scheduleRepository.findByClassroomId(classroomId)
        return scheduleMapper.scheduleListToScheduleDetailsList(scheduleList)
    }
}

