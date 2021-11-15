package com.glogachev.myfavoriteteam.data

import com.glogachev.myfavoriteteam.data.models.TeamNW
import retrofit2.Response
import retrofit2.http.GET

interface TeamApi {

    @GET("users")
    suspend fun fetchTeamInfo(): Response<TeamNW>
}