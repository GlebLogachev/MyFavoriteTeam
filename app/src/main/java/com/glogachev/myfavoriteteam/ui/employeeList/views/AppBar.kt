package com.glogachev.myfavoriteteam.ui.employeeList.views

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.glogachev.myfavoriteteam.R
import com.glogachev.myfavoriteteam.ui.employeeList.EmployeeListState
import com.glogachev.myfavoriteteam.ui.employeeList.SortingTypes
import com.glogachev.myfavoriteteam.ui.theme.MyFavoriteTeamTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
fun AppBar(
    modifier: Modifier = Modifier,
    state: EmployeeListState.Display,
    searchValueListener: (String) -> Unit,
    coroutineScope: CoroutineScope,
    bottomSheetScaffoldState: BottomSheetScaffoldState
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 18.dp)
            .clip(RoundedCornerShape(16.dp)),
        color = MyFavoriteTeamTheme.colors.secondaryBackground
    ) {
        Row(
            modifier = modifier
                .padding(bottom = 10.dp, top = 10.dp, start = 14.dp, end = 14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val keyboardController = LocalSoftwareKeyboardController.current
            val localFocusManager = LocalFocusManager.current
            Image(painter = painterResource(id = R.drawable.ic_search), contentDescription = null)
            BasicTextField(
                modifier = modifier
                    .padding(start = 8.dp)
                    .weight(1f)
                    .onFocusChanged {
                        if (!it.isFocused) {
                            localFocusManager.clearFocus()
                        }
                    },
                value = (state as? EmployeeListState.Display)?.searchValue.toString(),
                enabled = !state.loadingData,
                onValueChange = { newValue ->
                    if (newValue.length < 20) {
                        searchValueListener(newValue)
                    }
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        localFocusManager.clearFocus()
                        keyboardController?.hide()
                    }
                ),
                singleLine = true,
                textStyle = MyFavoriteTeamTheme.typography.medium15,
                cursorBrush = SolidColor(MyFavoriteTeamTheme.colors.actionText)
            )
            val actualFilteringIcon = if (state.sortingType == SortingTypes.ALPHABET) {
                R.drawable.ic_filter_inactive
            } else {
                R.drawable.ic_filter_active
            }
            Image(
                painter = painterResource(id = actualFilteringIcon),
                contentDescription = null,
                modifier = Modifier.clickable {
                    coroutineScope.launch {
                        if (bottomSheetScaffoldState.bottomSheetState.isCollapsed) {
                            bottomSheetScaffoldState.bottomSheetState.expand()
                        }
                    }
                }
            )
        }
    }
}