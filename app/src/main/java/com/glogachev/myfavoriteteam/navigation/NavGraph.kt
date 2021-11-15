package com.glogachev.myfavoriteteam.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.glogachev.myfavoriteteam.domain.model.Employee
import com.glogachev.myfavoriteteam.ui.detail.DetailsScreen
import com.glogachev.myfavoriteteam.ui.employeeList.EmployeeListScreen
import com.glogachev.myfavoriteteam.ui.employeeList.EmployeeListViewModel
import com.google.gson.Gson

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
fun SetupNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.List.route
    ) {
        composable(
            route = Screen.List.route
        ) {
            val employeeViewModel = hiltViewModel<EmployeeListViewModel>()
            EmployeeListScreen(
                navController = navController,
                employeesViewModel = employeeViewModel
            )
        }
        composable(
            route = Screen.Details.route,
            arguments = listOf(
                navArgument(DETAIL_ARGUMENT_KEY) {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            backStackEntry.arguments?.getString(DETAIL_ARGUMENT_KEY)?.let { json ->
                val employee = Gson().fromJson<Employee>(json, Employee::class.java)
                DetailsScreen(
                    navController = navController,
                    employee = employee
                )
            }

        }
    }
}