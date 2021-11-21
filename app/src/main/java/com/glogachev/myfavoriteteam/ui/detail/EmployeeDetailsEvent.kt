package com.glogachev.myfavoriteteam.ui.detail

sealed class EmployeeDetailsEvent {
    data class EnterScreen(val employeeString: String) : EmployeeDetailsEvent()
}