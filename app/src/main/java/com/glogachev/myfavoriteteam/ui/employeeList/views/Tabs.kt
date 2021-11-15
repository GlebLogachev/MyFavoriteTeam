package com.glogachev.myfavoriteteam.ui.employeeList.views

import androidx.compose.foundation.layout.heightIn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.glogachev.myfavoriteteam.ui.employeeList.DepartmentType
import com.glogachev.myfavoriteteam.ui.employeeList.EmployeeListState
import com.glogachev.myfavoriteteam.ui.theme.MyFavoriteTeamTheme

@ExperimentalMaterialApi
@Composable
fun Tabs(
    state: EmployeeListState.Display,
    onTabClick: (DepartmentType) -> Unit
) {
    ScrollableTabRow(
        selectedTabIndex = state.departmentType.ordinal,
        backgroundColor = Color.White,
        edgePadding = 28.dp,
        contentColor = MyFavoriteTeamTheme.colors.actionText
    ) {
        DepartmentType.values().forEach { type ->
            val isSelected = state.departmentType.ordinal == type.ordinal
            Tab(
                modifier = Modifier.heightIn(max = 36.dp),
                selected = isSelected,
                onClick = {
                    onTabClick(type)
                },
                text = {
                    Text(
                        text = stringResource(id = type.nameResId),
                        color = if (isSelected) {
                            MyFavoriteTeamTheme.colors.primaryText
                        } else {
                            MyFavoriteTeamTheme.colors.secondaryText
                        },
                        style = if (isSelected) {
                            MyFavoriteTeamTheme.typography.semiBold15
                        } else {
                            MyFavoriteTeamTheme.typography.medium15
                        }
                    )
                },
                selectedContentColor = Color.White
            )
        }
    }
}