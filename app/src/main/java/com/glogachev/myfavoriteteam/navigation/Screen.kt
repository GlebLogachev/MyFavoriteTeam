package com.glogachev.myfavoriteteam.navigation

const val DETAIL_ARGUMENT_KEY = "employee"

sealed class Screen(val route: String) {
    object List : Screen(route = "list_screen")
    object Details : Screen(route = "details_screen/{$DETAIL_ARGUMENT_KEY}") {
        fun passEmployee(employee: String): String {
            return "details_screen/$employee"
        }
    }
}
