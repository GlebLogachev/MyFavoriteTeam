package com.glogachev.myfavoriteteam.data

import com.glogachev.myfavoriteteam.domain.TeamRepository
import com.glogachev.myfavoriteteam.domain.mappers.toDomain
import com.glogachev.myfavoriteteam.domain.model.Employee
import javax.inject.Inject

class TeamRepositoryImpl @Inject constructor(
    private val api: TeamApi
) : TeamRepository {
    override suspend fun fetchTeamInfo(): List<Employee> {
        return api.fetchTeamInfo().body()?.toDomain()!!
    }
}
