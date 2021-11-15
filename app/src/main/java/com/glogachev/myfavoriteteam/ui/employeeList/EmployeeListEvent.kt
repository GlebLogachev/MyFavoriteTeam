package com.glogachev.myfavoriteteam.ui.employeeList

sealed class EmployeeListEvent {
    data class SearchValueChanged(val newValue: String) : EmployeeListEvent()
    data class SelectTab(val newType: DepartmentType) : EmployeeListEvent()
    data class SelectFilter(val filterType: SortingTypes) : EmployeeListEvent()
    object EnterScreen : EmployeeListEvent()
    object ReloadScreen : EmployeeListEvent()
}