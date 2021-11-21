package com.glogachev.myfavoriteteam.ui.employeeList.views

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import com.glogachev.myfavoriteteam.ui.employeeList.EmployeeListState
import com.glogachev.myfavoriteteam.ui.theme.MyFavoriteTeamTheme
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.google.gson.Gson

@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
fun EmployeesList(
    modifier: Modifier = Modifier,
    state: EmployeeListState.Display,
    gson : Gson,
    refreshList: () -> Unit,
    navController: NavController
) {
    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing = state.isRefreshing),
        onRefresh = { refreshList() },
        indicator = { indicatorState, trigger ->
            SwipeRefreshIndicator(
                state = indicatorState,
                refreshTriggerDistance = trigger,
                contentColor = MyFavoriteTeamTheme.colors.actionText
            )

        }
    ) {
        LazyColumn {
            itemsIndexed(
                items = state.displayEmployeesList
            ) { index, employee ->
                EmployeesListItem(
                    employee = employee,
                    isFirstItem = index == 0,
                    gson = gson,
                    navController = navController,
                    state = state
                )
            }
        }
    }

}