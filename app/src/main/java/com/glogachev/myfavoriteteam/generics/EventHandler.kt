package com.glogachev.myfavoriteteam.generics

interface EventHandler<T> {
    fun obtainEvent(event: T)
}