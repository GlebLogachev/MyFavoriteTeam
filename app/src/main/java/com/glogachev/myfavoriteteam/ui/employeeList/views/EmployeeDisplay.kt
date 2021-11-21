package com.glogachev.myfavoriteteam.ui.employeeList.views

import androidx.compose.foundation.layout.Column
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import com.glogachev.myfavoriteteam.ui.employeeList.DepartmentType
import com.glogachev.myfavoriteteam.ui.employeeList.EmployeeListState
import com.glogachev.myfavoriteteam.ui.employeeList.SortingTypes
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope

@ExperimentalCoilApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
fun EmployeeDisplay(
    navController: NavController,
    state: EmployeeListState.Display,
    modifier: Modifier = Modifier,
    gson: Gson,
    searchValueListener: (String) -> Unit,
    onTabClick: (DepartmentType) -> Unit,
    selectFilter: (SortingTypes) -> Unit,
    refreshList: () -> Unit
) {
    val bottomSheetScaffoldState: BottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberBottomSheetState(BottomSheetValue.Collapsed)
    )
    val coroutineScope: CoroutineScope = rememberCoroutineScope()

    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetPeekHeight = 0.dp,
        sheetBackgroundColor = Color.Transparent,
        sheetElevation = 0.dp,
        sheetContent = {
            BottomSheetContent(
                state = state,
                bottomSheetScaffoldState = bottomSheetScaffoldState,
                coroutineScope = coroutineScope,
                selectFilter = selectFilter
            )
        },
        topBar = {
            AppBar(
                state = state,
                searchValueListener = searchValueListener,
                coroutineScope = coroutineScope,
                bottomSheetScaffoldState = bottomSheetScaffoldState
            )
        }
    ) {
        Column {
            Tabs(
                state = state,
                onTabClick = onTabClick
            )
            if (state.loadingData) {
                EmployeeLoading()
            } else {
                if (state.displayEmployeesList.isEmpty()) {
                    EmptyEmployeeList()
                } else {
                    EmployeesList(
                        state = state,
                        refreshList = refreshList,
                        navController = navController,
                        gson = gson
                    )
                }
            }
        }
    }
}
