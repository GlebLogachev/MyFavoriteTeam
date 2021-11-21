package com.glogachev.myfavoriteteam.ui.detail

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.glogachev.myfavoriteteam.R
import com.glogachev.myfavoriteteam.ui.theme.MyFavoriteTeamTheme
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit


@ExperimentalCoilApi
@Composable
fun DetailsScreen(
    navController: NavController,
    employeeDetailsViewModel: EmployeeDetailsViewModel,
    employeeString: String
) {
    val viewState = employeeDetailsViewModel.state.collectAsState()
    when (val state = viewState.value) {
        is EmployeeDetailsState.Display -> EmployeeDetailsContent(
            state = state,
            navController = navController
        )
        EmployeeDetailsState.Loading -> {
            Loading()
        }
    }

    LaunchedEffect(key1 = viewState, block = {
        employeeDetailsViewModel.obtainEvent(event = EmployeeDetailsEvent.EnterScreen(employeeString = employeeString))
    })
}

@Composable
fun EmployeeDetailsContent(
    state: EmployeeDetailsState.Display,
    navController: NavController,
) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MyFavoriteTeamTheme.colors.secondaryBackground)
            .padding(top = 46.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp)
        ) {
            Image(
                painter = painterResource(
                    id = R.drawable.ic_route_back
                ),
                contentDescription = null,
                modifier = Modifier
                    .padding(start = 16.dp)
                    .clickable {
                        navController.popBackStack()
                    }
            )
        }
        Image(
            painter = rememberImagePainter(
                data = state.employee.avatarUrl,
                builder = {
                    crossfade(true)
                    placeholder(R.drawable.ic_goose_plug)
                    error(R.drawable.nlo_error)
                },
            ),
            contentDescription = null,
            modifier = Modifier
                .size(104.dp)
                .clip(CircleShape)

        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "${state.employee.firstName}  ${state.employee.lastName}",
                color = MyFavoriteTeamTheme.colors.primaryText,
                style = MyFavoriteTeamTheme.typography.bold24,
                modifier = Modifier.alignByBaseline()
            )
            Text(
                text = state.employee.userTag.lowercase(),
                color = MyFavoriteTeamTheme.colors.secondaryText,
                style = MyFavoriteTeamTheme.typography.regular17,
                modifier = Modifier
                    .padding(start = 4.dp)
                    .alignByBaseline()
            )
        }

        Text(
            text = stringResource(id = state.employee.department.nameResId),
            color = MyFavoriteTeamTheme.colors.descriptionText,
            style = MyFavoriteTeamTheme.typography.regular13,
            modifier = Modifier.padding(top = 12.dp, bottom = 24.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp, start = 16.dp, end = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(
                        id = R.drawable.ic_star_image
                    ),
                    contentDescription = null
                )
                val birthday = state.employee.birthday
                    .format(DateTimeFormatter.ofPattern("d MMMM yyyy"))
                    .replace(".", "")

                Text(
                    text = birthday,
                    color = MyFavoriteTeamTheme.colors.descriptionText,
                    style = MyFavoriteTeamTheme.typography.medium16,
                    modifier = Modifier.padding(start = 14.dp)

                )
                Spacer(modifier = Modifier.weight(1F))

                val years =
                    ChronoUnit.YEARS.between(state.employee.birthday, LocalDate.now()).toString()
                Text(
                    text = years,
                    color = MyFavoriteTeamTheme.colors.secondaryText,
                    style = MyFavoriteTeamTheme.typography.medium16,
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp)
                    .clickable {
                        val intent =
                            Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + state.employee.phone))
                        startActivity(context, intent, null)
                    }
                    .padding(top = 20.dp, start = 16.dp, end = 20.dp, bottom = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(
                        id = R.drawable.ic_phone
                    ),
                    contentDescription = null
                )
                Text(
                    text = state.employee.phone,
                    color = MyFavoriteTeamTheme.colors.descriptionText,
                    style = MyFavoriteTeamTheme.typography.medium16,
                    modifier = Modifier.padding(start = 14.dp)
                )
            }
        }

    }
}

@Composable
fun Loading() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator()
    }
}

