package com.glogachev.myfavoriteteam.ui.employeeList

import com.glogachev.myfavoriteteam.domain.model.Employee

sealed class EmployeeListState {
    data class Display(
        val loadingData: Boolean = true,
        val initialEmployeeList: List<Employee> = emptyList(),
        val searchValue: String = "",
        val departmentType: DepartmentType = DepartmentType.ALL,
        val sortingType: SortingTypes = SortingTypes.ALPHABET,
        val isRefreshing: Boolean = false
    ) : EmployeeListState() {
        val displayEmployeesList: List<Employee> = filterInitListList()

        private fun filterInitListList(): List<Employee> {
            return initialEmployeeList
                .filter {
                    it.firstName.contains(searchValue, true) ||
                            it.lastName.contains(searchValue, true) ||
                            it.userTag.contains(searchValue, true)

                }
                .filter {
                    return@filter if (departmentType == DepartmentType.ALL) {
                        true
                    } else {
                        it.department == departmentType
                    }
                }
                .currentSorting(sortingType)

        }
    }

    object Error : EmployeeListState()
}

private fun List<Employee>.currentSorting(sortingType: SortingTypes): List<Employee> {
    return if (sortingType == SortingTypes.ALPHABET) {
        this.sortedBy { it.firstName }
    } else {
        this.sortedBy { it.birthday }
    }
}