package com.glogachev.myfavoriteteam.domain.model

import com.glogachev.myfavoriteteam.ui.employeeList.DepartmentType
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import java.time.LocalDate


data class Employee(
    val avatarUrl: String,
    val birthday: LocalDate,
    val department: DepartmentType,
    val firstName: String,
    val id: String,
    val lastName: String,
    val phone: String,
    val position: String,
    val userTag: String
)

class LocalDateAdapter : TypeAdapter<LocalDate?>() {
    override fun read(jsonReader: JsonReader): LocalDate {
        return LocalDate.parse(jsonReader.nextString())
    }

    override fun write(out: JsonWriter, value: LocalDate?) {
        out.value(value.toString())
    }
}