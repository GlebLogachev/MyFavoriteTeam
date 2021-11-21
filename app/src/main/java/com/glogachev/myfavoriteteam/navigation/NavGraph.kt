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
import coil.annotation.ExperimentalCoilApi
import com.glogachev.myfavoriteteam.domain.model.LocalDateAdapter
import com.glogachev.myfavoriteteam.ui.detail.DetailsScreen
import com.glogachev.myfavoriteteam.ui.detail.EmployeeDetailsViewModel
import com.glogachev.myfavoriteteam.ui.employeeList.EmployeeListScreen
import com.glogachev.myfavoriteteam.ui.employeeList.EmployeeListViewModel
import com.google.gson.GsonBuilder
import java.time.LocalDate

@ExperimentalCoilApi
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
            val gson =
                GsonBuilder()
                    .registerTypeAdapter(LocalDate::class.java, LocalDateAdapter())
                    .create()
            EmployeeListScreen(
                navController = navController,
                employeesViewModel = employeeViewModel,
                gson = gson
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
                val employeeDetailsViewModel = hiltViewModel<EmployeeDetailsViewModel>()
                DetailsScreen(
                    navController = navController,
                    employeeDetailsViewModel = employeeDetailsViewModel,
                    employeeString = json
                )
            }

        }
    }
}