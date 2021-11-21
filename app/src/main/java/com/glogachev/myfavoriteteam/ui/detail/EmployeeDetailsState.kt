package com.glogachev.myfavoriteteam.ui.detail

import com.glogachev.myfavoriteteam.domain.model.Employee

sealed class EmployeeDetailsState {
    data class Display(val employee: Employee) : EmployeeDetailsState()
    object Loading : EmployeeDetailsState()
}
