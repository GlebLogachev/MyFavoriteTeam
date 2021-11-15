package com.glogachev.myfavoriteteam.ui.employeeList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.glogachev.myfavoriteteam.domain.TeamRepository
import com.glogachev.myfavoriteteam.generics.EventHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@HiltViewModel
class EmployeeListViewModel @Inject constructor(
    private val repository: TeamRepository
) : ViewModel(), EventHandler<EmployeeListEvent> {

    private val _state: MutableStateFlow<EmployeeListState> =
        MutableStateFlow(EmployeeListState.Display())
    val state: StateFlow<EmployeeListState> = _state.asStateFlow()

    override fun obtainEvent(event: EmployeeListEvent) {
        when (val currentState = _state.value) {
            is EmployeeListState.Error -> reduce(event = event, currentState = currentState)
            is EmployeeListState.Display -> reduce(event = event, currentState = currentState)
        }
    }

    private fun reduce(event: EmployeeListEvent, currentState: EmployeeListState.Error) {
        when (event) {
            EmployeeListEvent.ReloadScreen -> {
                fetchEmployeeList()
            }
        }
    }

    private fun reduce(event: EmployeeListEvent, currentState: EmployeeListState.Display) {
        when (event) {
            EmployeeListEvent.EnterScreen -> fetchEmployeeList()
            EmployeeListEvent.ReloadScreen -> refreshList(currentState = currentState)
            is EmployeeListEvent.SearchValueChanged -> {
                _state.value = currentState.copy(
                    searchValue = event.newValue
                )
            }
            is EmployeeListEvent.SelectTab -> {
                _state.value = currentState.copy(
                    departmentType = event.newType
                )
            }
            is EmployeeListEvent.SelectFilter -> {
                _state.value = currentState.copy(
                    sortingType = event.filterType
                )
            }
        }
    }

    private fun refreshList(currentState: EmployeeListState.Display) {
        _state.value = currentState.copy(
            isRefreshing = true
        )
        fetchEmployeeList()
    }

    private fun fetchEmployeeList() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val employees = repository
                    .fetchTeamInfo()
                withContext(Dispatchers.Main) {
                    when (val state = _state.value) {
                        is EmployeeListState.Display -> {
                            val newState = state.copy(
                                initialEmployeeList = employees,
                                loadingData = false,
                                isRefreshing = false
                            )
                            _state.value = newState
                        }
                        else -> {
                            _state.value = EmployeeListState.Display(
                                initialEmployeeList = employees,
                                loadingData = false,
                                isRefreshing = false
                            )
                        }
                    }
                }
            } catch (ex: Throwable) {
                determineError()
            }
        }
    }

    private fun determineError() {
        if (_state.value is EmployeeListState.Display) {
            if ((_state.value as EmployeeListState.Display).initialEmployeeList.isEmpty()) {
                _state.value = EmployeeListState.Error
            } else {
                _state.value = (_state.value as EmployeeListState.Display).copy(
                    loadingData = false,
                    isRefreshing = false
                )
            }
        } else {
            _state.value = EmployeeListState.Error
        }
    }

}

