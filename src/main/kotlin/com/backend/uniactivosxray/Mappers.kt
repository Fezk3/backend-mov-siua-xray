package com.backend.uniactivosxray

import org.mapstruct.*
import java.time.LocalDateTime

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface PrivilegeMapper {
    fun privilegeListToPrivilegeDetailsList(
        privilegeList: Set<Privilege>?,
    ): Set<PrivilegeDetails>
}

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface RoleMapper {
    fun roleListToRoleDetailsList(
        roleList: Set<Role>?,
    ): Set<RoleDetails>
}

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface CollegeMapper {
    fun collegeToCollegeDetails(
        priority: College,
    ): CollegeDetails

    fun collegeListToCollegeDetailsList(
        collegeList: Set<College>,
    ): Set<CollegeDetails>
}

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface AssetTypeMapper {
    fun assetTypeToAssetTypeDetails(
        assetType: AssetType,
    ): AssetTypeDetails

    fun assetTypeListToAssetTypeDetailsList(
        assetTypeList: Set<AssetType>,
    ): Set<AssetTypeDetails>
}

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface AssetsMapper {
    fun assetsToAssetsDetails(
        assets: Assets,
    ): AssetsDetails

    fun assetsListToAssetsDetailsList(
        assetsList: Set<Assets>,
    ): Set<AssetsDetails>
}

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface ClassroomMapper {
    fun classroomToClassroomDetails(
        classroom: Classroom,
    ): ClassroomDetails

    fun classroomListToClassroomDetailsList(
        classroomList: List<Classroom>,
    ): List<ClassroomDetails>
}

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface FormMapper {
    fun formToFormDetails(
        form: Form,
    ): FormDetails

    fun formListToFormDetailsList(
        formList: Set<Form>,
    ): Set<FormDetails>
}

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface StatusMapper {
    fun statusToStatusDetails(
        status: State,
    ): StatusDetails

    fun statusListToStatusDetailsList(
        statusList: Set<State>,
    ): Set<StatusDetails>
}

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface ScheduleMapper {
    fun scheduleToScheduleDetails(
        schedule: Schedule,
    ): ScheduleDetails

    fun scheduleListToScheduleDetailsList(
        scheduleList: Set<Schedule>,
    ): Set<ScheduleDetails>
}

 @Mapper(
     imports = [LocalDateTime::class],
     componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE
 )
interface FormHistoryMapper {
    fun formHistoryToFormHistoryDetails(
        formHistory: FormHistory,
    ): FormHistoryDetails

    fun formHistoryListToFormHistoryDetailsList(
        formHistoryList: Set<FormHistory>,
    ): Set<FormHistoryDetails>

     @Mapping(target = "date", defaultExpression = "java(new java.util.Date())")
     fun FormHistoryInputToFormHistory(
         formHistoryInput: FormHistoryInput,
     ): FormHistory

     @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
     fun formHistoryInputToFormHistory(dto: FormHistoryInput, @MappingTarget formHistory: FormHistory)
}

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface UserMapper {
    fun userToUserDetails(
        user: User,
    ): UserDetails

     fun userLoginInputToUser(
         userLoginInput: UserLoginInput,
     ): User

     @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
     fun taskInputToTask(dto: UserLoginInput, @MappingTarget users: User)
}