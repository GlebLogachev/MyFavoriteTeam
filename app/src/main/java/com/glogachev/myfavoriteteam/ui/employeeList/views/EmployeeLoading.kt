package com.glogachev.myfavoriteteam.ui.employeeList.views

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun EmployeeLoading() {
    Column(
        modifier = Modifier
            .padding(start = 16.dp, top = 18.dp, end = 16.dp)
            .fillMaxSize()
            .background(Color.White)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            repeat(10) {
                item {
                    ShimmerAnimateItem()
                }
            }
        }

    }
}

@Composable
fun ShimmerAnimateItem() {
    val shimmerColors = listOf(
        Color.LightGray.copy(0.9f),
        Color.LightGray.copy(0.2f),
        Color.LightGray.copy(0.9f)
    )

    val transition = rememberInfiniteTransition()
    val translateAnim = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            tween(durationMillis = 1000, easing = FastOutLinearInEasing),
            RepeatMode.Reverse
        )
    )

    val brush = Brush.linearGradient(
        colors = shimmerColors,
        start = Offset(10f, 10f),
        end = Offset(translateAnim.value, translateAnim.value)
    )
    ShimmerBrush(brush)
}

@Composable
fun ShimmerBrush(brush: Brush) {
    Column(
        modifier = Modifier
            .background(Color.White)
            .padding(bottom = 24.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize()
        ) {

            Spacer(
                modifier = Modifier
                    .clip(CircleShape)
                    .width(72.dp)
                    .height(72.dp)
                    .background(brush)
            )
            Column(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .fillMaxSize()
                    .padding(16.dp, 0.dp, 0.dp, 0.dp)
            ) {
                Spacer(
                    modifier = Modifier
                        .clip(CircleShape)
                        .width(144.dp)
                        .height(16.dp)
                        .background(brush)
                )
                Spacer(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .clip(CircleShape)
                        .height(12.dp)
                        .width(80.dp)
                        .background(brush)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EmployeeLoading_preview() {
    Box {
        EmployeeLoading()
    }
}