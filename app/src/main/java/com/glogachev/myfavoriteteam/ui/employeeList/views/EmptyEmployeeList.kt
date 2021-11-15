package com.glogachev.myfavoriteteam.ui.employeeList.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.glogachev.myfavoriteteam.R
import com.glogachev.myfavoriteteam.ui.theme.MyFavoriteTeamTheme

@Composable
fun EmptyEmployeeList() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 80.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_empty_search), contentDescription = null,
            modifier = Modifier
                .width(56.dp)
                .height(56.dp)
        )

        Text(
            text = stringResource(R.string.empty_search_result),
            color = MyFavoriteTeamTheme.colors.primaryText,
            style = MyFavoriteTeamTheme.typography.semiBold17,
            modifier = Modifier.padding(top = 8.dp)
        )
        Text(
            text = stringResource(R.string.empty_search_result_description),
            color = MyFavoriteTeamTheme.colors.secondaryText,
            style = MyFavoriteTeamTheme.typography.regular16,
            modifier = Modifier.padding(top = 12.dp)
        )
    }
}