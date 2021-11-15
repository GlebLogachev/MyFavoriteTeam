package com.glogachev.myfavoriteteam.domain.mappers

import com.glogachev.myfavoriteteam.data.models.EmployeeNW
import com.glogachev.myfavoriteteam.data.models.TeamNW
import com.glogachev.myfavoriteteam.domain.model.Employee
import com.glogachev.myfavoriteteam.ui.employeeList.DepartmentType

fun TeamNW.toDomain(): List<Employee> {
    return employees
        .map {
            it.toDomain()
        }
}

fun EmployeeNW.toDomain(): Employee {
    return Employee(
        avatarUrl = avatarUrl,
        birthdayStr = birthday,
        department = department.toDepartmentType(),
        firstName = firstName,
        id = id,
        lastName = lastName,
        phone = phone,
        position = position,
        userTag = userTag
    )
}

private fun String.toDepartmentType(): DepartmentType {
    return when (this.lowercase()) {
        DepartmentType.ANDROID.name.lowercase() -> DepartmentType.ANDROID
        DepartmentType.IOS.name.lowercase() -> DepartmentType.IOS
        DepartmentType.DESIGN.name.lowercase() -> DepartmentType.DESIGN
        DepartmentType.MANAGEMENT.name.lowercase() -> DepartmentType.MANAGEMENT
        DepartmentType.QA.name.lowercase() -> DepartmentType.QA
        DepartmentType.BACK_OFFICE.name.lowercase() -> DepartmentType.BACK_OFFICE
        DepartmentType.FRONTEND.name.lowercase() -> DepartmentType.FRONTEND
        DepartmentType.HR.name.lowercase() -> DepartmentType.HR
        DepartmentType.PR.name.lowercase() -> DepartmentType.PR
        DepartmentType.BACKEND.name.lowercase() -> DepartmentType.BACKEND
        DepartmentType.SUPPORT.name.lowercase() -> DepartmentType.SUPPORT
        DepartmentType.ANALYTICS.name.lowercase() -> DepartmentType.ANALYTICS
        else -> DepartmentType.ANDROID
    }
}