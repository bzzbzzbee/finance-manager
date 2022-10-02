package ru.forum.myapplication.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.forum.myapplication.CONST.OPERATION_TABLE

@Entity(tableName = OPERATION_TABLE)
data class OperationDbModel(
    @PrimaryKey(autoGenerate = true)
    var operationId: Long,
    val amount: Double,
    val comment: String? = null,
    val date: String
)
