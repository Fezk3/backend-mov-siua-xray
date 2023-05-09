package com.backend.uniactivosxray

import com.fasterxml.jackson.annotation.JsonFormat
import java.util.*

data class UserLoginInput(
    var email: String = "",
    var password: String = "",
)

data class UserDetails(
    var id: Long? = null,
    var email: String? = null,
    var name: String? = null,
    var lastName: String? = null,
    var roleList: List<RoleDetails>? = null,
)

data class PriorityDetails(
    var id: Long? = null,
    var label: String? = null,
)

data class PrivilegeDetails(
    var id: Long? = null,
    var name: String? = null,
)

data class RoleDetails(
    var id: Long? = null,
    var name: String? = null,
    var privileges: List<PrivilegeDetails>? = null,
)

data class CollegeDetails(
    var id: Long? = null,
    var name: String? = null,
)

data class AssetTypeDetails(
    var id: Long? = null,
    var description: String? = null,
)

data class AssetsDetails(
    var id: Long? = null,
    var name: String? = null,
    var assetType: AssetTypeDetails? = null,
    var classroomid: ClassroomDetails? = null,
)

data class ClassroomDetails(
    var id: Long? = null,
    var classNumber: String? = null,
    var college: CollegeDetails? = null,
    var assets: List<AssetsDetails>? = null,
)

data class FormDetails(
    var id: Long? = null,
    var classroom: ClassroomDetails? = null,
)

data class StatusDetails(
    var id: Long? = null,
    var description: String? = null,
)

data class FormHistoryDetails(
    var id: Long? = null,
    var form: FormDetails? = null,
    var userId: Long? = null,
    var date: Date? = null,
    var status: StatusDetails? = null,
)

data class FormHistoryInput(
    var formId: Long? = null,
    var userId: Long? = null,
    var statusId: Long? = null,
    @JsonFormat(pattern="dd/MM/yyyy")
    var date: Date? = null,
)

data class ScheduleDetails(
    var id: Long? = null,
    var classroom: ClassroomDetails? = null,
    var courseName: String? = null,
    var userId: Long? = null,
    var date: Date? = null,
    var startTime : Date? = null,
    var endTime : Date? = null,
)

