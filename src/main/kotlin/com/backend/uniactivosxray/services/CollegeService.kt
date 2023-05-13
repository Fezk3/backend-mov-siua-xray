package com.backend.uniactivosxray.services

import com.backend.uniactivosxray.College
import com.backend.uniactivosxray.CollegeDetails
import com.backend.uniactivosxray.CollegeMapper
import com.backend.uniactivosxray.CollegeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

interface CollegeService {

        fun getall(): List<CollegeDetails>

        fun getbyname(name: String): CollegeDetails

}
@Service
class CollegeServiceImpl(

        @Autowired
        val collegeRepository: CollegeRepository,
        @Autowired
        val collegeMapper: CollegeMapper,

) : CollegeService {

        override fun getall(): List<CollegeDetails> {
                val collegeList: List<College> = collegeRepository.findAll()
                return collegeMapper.collegeListToCollegeDetailsList(collegeList)
        }


        override fun getbyname(name: String): CollegeDetails {

                val college: College = collegeRepository.findByName(name).get()
                return collegeMapper.collegeToCollegeDetails(college)

        }

}