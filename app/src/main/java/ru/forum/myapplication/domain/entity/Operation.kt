package ru.forum.myapplication.domain.entity

data class Operation(
    var operationId: Long,
    val amount: Double,
    val comment: String? = null,
    val date: String,
    val tags: List<Tag>
)