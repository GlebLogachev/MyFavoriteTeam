package com.glogachev.myfavoriteteam.ui.employeeList.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.glogachev.myfavoriteteam.R
import com.glogachev.myfavoriteteam.ui.theme.MyFavoriteTeamTheme

@Composable
fun EmployeeError(
    retryLoading: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .height(56.dp)
                .width(56.dp),
            painter = painterResource(id = R.drawable.nlo_error), contentDescription = null
        )
        Text(
            modifier = Modifier.padding(top = 8.dp),
            text = stringResource(id = R.string.someone_broke_everything),
            color = MyFavoriteTeamTheme.colors.primaryText,
            style = MyFavoriteTeamTheme.typography.semiBold17
        )
        Text(
            modifier = Modifier.padding(top = 12.dp),
            text = stringResource(id = R.string.we_will_try_to_fix),
            color = MyFavoriteTeamTheme.colors.secondaryText,
            style = MyFavoriteTeamTheme.typography.regular16
        )
        Text(
            modifier = Modifier
                .padding(top = 12.dp)
                .clickable {
                    retryLoading.invoke()
                },
            text = stringResource(id = R.string.try_again),
            color = MyFavoriteTeamTheme.colors.actionText,
            style = MyFavoriteTeamTheme.typography.semiBold16
        )
    }
}

@Preview(showBackground = true)
@Composable
fun EmployeeError_preview() {
    Box {
        EmployeeError {}
    }
}
