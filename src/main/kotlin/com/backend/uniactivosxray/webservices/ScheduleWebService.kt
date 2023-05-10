package com.backend.uniactivosxray.webservices

import com.backend.uniactivosxray.ScheduleDetails
import com.backend.uniactivosxray.services.ScheduleService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/schedules")
class ScheduleController(private val scheduleService: ScheduleService) {

    @GetMapping
    @ResponseBody
    fun getAll(): List<ScheduleDetails> {
        return scheduleService.getAll()
    }


    @GetMapping("/schedules/{userId}")
    @ResponseBody
    fun getByUserId(@PathVariable userId: Long): List<ScheduleDetails> {
        return scheduleService.getByUserId(userId)
    }

    @GetMapping("/schedules/{classroomId}")
    @ResponseBody
    fun getByClassroomId(@PathVariable classroomId: Long): List<ScheduleDetails> {
        return scheduleService.getByClassroomId(classroomId)
    }

}