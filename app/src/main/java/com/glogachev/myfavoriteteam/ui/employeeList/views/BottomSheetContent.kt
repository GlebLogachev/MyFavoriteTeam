package com.glogachev.myfavoriteteam.ui.employeeList.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults.colors
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.glogachev.myfavoriteteam.R
import com.glogachev.myfavoriteteam.ui.employeeList.EmployeeListState
import com.glogachev.myfavoriteteam.ui.employeeList.SortingTypes
import com.glogachev.myfavoriteteam.ui.theme.MyFavoriteTeamTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun BottomSheetContent(
    state: EmployeeListState.Display,
    bottomSheetScaffoldState: BottomSheetScaffoldState,
    coroutineScope: CoroutineScope,
    selectFilter: (SortingTypes) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.1f))
    ) {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .weight(2f)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(start = 8.dp, end = 8.dp)
                .clip(RoundedCornerShape(topStartPercent = 5, topEndPercent = 5))
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(
                modifier = Modifier
                    .padding(top = 10.dp)
                    .width(56.dp)
                    .height(4.dp)
                    .background(MyFavoriteTeamTheme.colors.hintColor)
            )
            Text(
                modifier = Modifier.padding(top = 12.dp),
                text = stringResource(id = R.string.sorting),
                style = MyFavoriteTeamTheme.typography.semiBold20,
                color = MyFavoriteTeamTheme.colors.primaryText
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top=20.dp)
                    .clickable {
                        selectAlphabet(coroutineScope, bottomSheetScaffoldState, selectFilter)
                    }
                    .padding(start = 18.dp, end = 18.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = state.sortingType == SortingTypes.ALPHABET,
                    onClick = {
                        selectAlphabet(
                            coroutineScope,
                            bottomSheetScaffoldState,
                            selectFilter
                        )
                    },
                    colors = colors(
                        selectedColor = MyFavoriteTeamTheme.colors.actionText,
                        unselectedColor = MyFavoriteTeamTheme.colors.actionText
                    )
                )
                Text(
                    text = stringResource(id = R.string.alphabetically),
                    color = MyFavoriteTeamTheme.colors.primaryText,
                    style = MyFavoriteTeamTheme.typography.medium16
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top =10.dp)
                    .clickable {
                        selectBirthday(coroutineScope, bottomSheetScaffoldState, selectFilter)
                    }
                    .padding( start = 18.dp, end = 18.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = state.sortingType == SortingTypes.BIRTHDAY,
                    onClick = {
                        selectBirthday(
                            coroutineScope,
                            bottomSheetScaffoldState,
                            selectFilter
                        )
                    },
                    colors = colors(
                        selectedColor = MyFavoriteTeamTheme.colors.actionText,
                        unselectedColor = MyFavoriteTeamTheme.colors.actionText
                    )
                )
                Text(
                    text = stringResource(id = R.string.by_birthday),
                    color = MyFavoriteTeamTheme.colors.primaryText,
                    style = MyFavoriteTeamTheme.typography.medium16
                )
            }
        }
    }
}


@ExperimentalMaterialApi
private fun selectBirthday(
    coroutineScope: CoroutineScope,
    bottomSheetScaffoldState: BottomSheetScaffoldState,
    selectFilter: (SortingTypes) -> Unit
) {
    coroutineScope.launch {
        if (bottomSheetScaffoldState.bottomSheetState.isExpanded) {
            bottomSheetScaffoldState.bottomSheetState.collapse()
            selectFilter(SortingTypes.BIRTHDAY)
        }
    }
}

@ExperimentalMaterialApi
private fun selectAlphabet(
    coroutineScope: CoroutineScope,
    bottomSheetScaffoldState: BottomSheetScaffoldState,
    selectFilter: (SortingTypes) -> Unit
) {
    coroutineScope.launch {
        if (bottomSheetScaffoldState.bottomSheetState.isExpanded) {
            bottomSheetScaffoldState.bottomSheetState.collapse()
            selectFilter(SortingTypes.ALPHABET)
        }
    }
}