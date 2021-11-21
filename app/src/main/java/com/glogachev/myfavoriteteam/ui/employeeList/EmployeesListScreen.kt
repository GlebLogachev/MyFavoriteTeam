package com.glogachev.myfavoriteteam.ui.employeeList

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.glogachev.myfavoriteteam.ui.employeeList.views.EmployeeDisplay
import com.glogachev.myfavoriteteam.ui.employeeList.views.EmployeeError
import com.google.gson.Gson

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@Composable
fun EmployeeListScreen(
    navController: NavController,
    employeesViewModel: EmployeeListViewModel,
    gson: Gson,
    modifier: Modifier = Modifier
) {
    val viewState = employeesViewModel.state.collectAsState()

    when (val state = viewState.value) {
        is EmployeeListState.Error -> {
            EmployeeError(
                retryLoading = { employeesViewModel.obtainEvent(EmployeeListEvent.ReloadScreen) },
            )
        }
        is EmployeeListState.Display -> {
            EmployeeDisplay(
                navController = navController,
                state = state,
                modifier = modifier,
                gson = gson,
                { employeesViewModel.obtainEvent(EmployeeListEvent.SearchValueChanged(it)) },
                { employeesViewModel.obtainEvent(EmployeeListEvent.SelectTab(it)) },
                { employeesViewModel.obtainEvent(EmployeeListEvent.SelectFilter(it)) },
                { employeesViewModel.obtainEvent(EmployeeListEvent.ReloadScreen) }
            )
        }
    }
    LaunchedEffect(key1 = viewState, block = {
        employeesViewModel.obtainEvent(event = EmployeeListEvent.EnterScreen)
    })
}




