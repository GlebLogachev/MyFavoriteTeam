package com.glogachev.myfavoriteteam.domain

import com.glogachev.myfavoriteteam.domain.model.Employee

interface TeamRepository {
    suspend fun fetchTeamInfo(): List<Employee>
}