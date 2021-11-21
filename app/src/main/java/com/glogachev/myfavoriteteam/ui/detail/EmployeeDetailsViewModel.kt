package com.glogachev.myfavoriteteam.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.glogachev.myfavoriteteam.domain.model.Employee
import com.glogachev.myfavoriteteam.domain.model.LocalDateAdapter
import com.glogachev.myfavoriteteam.generics.EventHandler
import com.google.gson.GsonBuilder
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDate
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class EmployeeDetailsViewModel @Inject constructor() : ViewModel(),
    EventHandler<EmployeeDetailsEvent> {
    private var _state = MutableStateFlow<EmployeeDetailsState>(EmployeeDetailsState.Loading)
    val state: StateFlow<EmployeeDetailsState> = _state.asStateFlow()


    override fun obtainEvent(event: EmployeeDetailsEvent) {
        when (val currentState = _state.value) {
            is EmployeeDetailsState.Loading -> {
                reduce(event, currentState)
            }
        }
    }

    private fun reduce(event: EmployeeDetailsEvent, currentState: EmployeeDetailsState.Loading) {
        when (event) {
            is EmployeeDetailsEvent.EnterScreen -> {
                viewModelScope.launch(Dispatchers.IO) {
                    _state.emit(EmployeeDetailsState.Display(convertStringToEmployeeObj(event.employeeString)))
                }
            }
        }
    }

    private fun convertStringToEmployeeObj(employee: String): Employee {
        val gson = GsonBuilder()
            .registerTypeAdapter(LocalDate::class.java, LocalDateAdapter())
            .create()
        return gson.fromJson<Employee>(employee, Employee::class.java)
    }
}