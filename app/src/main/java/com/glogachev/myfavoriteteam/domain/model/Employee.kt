package com.glogachev.myfavoriteteam.domain.model

import com.glogachev.myfavoriteteam.ui.employeeList.DepartmentType
import java.time.LocalDate

data class Employee(
    val avatarUrl: String,
    val birthdayStr: String,
    val department: DepartmentType,
    val firstName: String,
    val id: String,
    val lastName: String,
    val phone: String,
    val position: String,
    val userTag: String
) {
    val birthday: LocalDate = birthdayStr.toLocalDate()
}

private fun String.toLocalDate(): LocalDate {
    return LocalDate.parse(this)
}