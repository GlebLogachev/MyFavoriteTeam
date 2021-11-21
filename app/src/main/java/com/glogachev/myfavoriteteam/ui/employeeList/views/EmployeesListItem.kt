package com.glogachev.myfavoriteteam.ui.employeeList.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.glogachev.myfavoriteteam.R
import com.glogachev.myfavoriteteam.domain.model.Employee
import com.glogachev.myfavoriteteam.navigation.Screen
import com.glogachev.myfavoriteteam.ui.employeeList.EmployeeListState
import com.glogachev.myfavoriteteam.ui.employeeList.SortingTypes
import com.glogachev.myfavoriteteam.ui.theme.MyFavoriteTeamTheme
import com.google.gson.Gson
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import java.time.format.DateTimeFormatter

@ExperimentalCoilApi
@Composable
fun EmployeesListItem(
    employee: Employee,
    isFirstItem: Boolean,
    gson: Gson,
    navController: NavController,
    state: EmployeeListState.Display
) {
    val topPadding = if (isFirstItem) {
        22.dp
    } else {
        0.dp
    }
    Row(
        modifier = Modifier
            .fillMaxSize()
            .clickable {
                val employeeJson =
                    gson.toJson(
                        employee.copy(
                            avatarUrl = URLEncoder.encode(
                                employee.avatarUrl,
                                StandardCharsets.UTF_8.toString()
                            ),
                        )
                    )
                navController.navigate(Screen.Details.passEmployee(employee = employeeJson))
            }
            .padding(
                start = 16.dp, end = 20.dp, bottom = 12.dp, top = topPadding
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberImagePainter(data = employee.avatarUrl,
                builder = {
                    crossfade(true)
                    placeholder(R.drawable.ic_goose_plug)
                    error(R.drawable.nlo_error)
                }
            ),
            contentDescription = null,
            modifier = Modifier
                .size(72.dp)
                .clip(CircleShape)

        )
        Column(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .weight(1f)
                .padding(start = 16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = stringResource(
                        R.string.full_name,
                        employee.firstName,
                        employee.lastName
                    ),
                    color = MyFavoriteTeamTheme.colors.primaryText,
                    style = MyFavoriteTeamTheme.typography.medium16
                )
                Text(
                    text = employee.userTag.lowercase(),
                    modifier = Modifier.padding(start = 4.dp),
                    color = MyFavoriteTeamTheme.colors.secondaryText,
                    style = MyFavoriteTeamTheme.typography.medium14
                )
            }

            Text(
                text = employee.position,
                modifier = Modifier.padding(top = 3.dp),
                color = MyFavoriteTeamTheme.colors.descriptionText,
                style = MyFavoriteTeamTheme.typography.regular13
            )
        }

        if (state.sortingType == SortingTypes.BIRTHDAY) {
            val formatter = DateTimeFormatter.ofPattern("d MMM")
            val formattedDate = employee.birthday.format(formatter)
            Text(
                text = formattedDate,
                color = MyFavoriteTeamTheme.colors.descriptionText,
                style = MyFavoriteTeamTheme.typography.regular15
            )
        }
    }
}