package ru.forum.myapplication.domain.vm

data class UiState<T>(
    val isLoading: Boolean = false,
    val error: Throwable? = null,
    val data: T? = null
)
