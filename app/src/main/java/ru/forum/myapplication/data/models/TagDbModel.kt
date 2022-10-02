package ru.forum.myapplication.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.forum.myapplication.CONST.TAG_TABLE

@Entity(tableName = TAG_TABLE)
data class TagDbModel(
    @PrimaryKey(autoGenerate = true)
    var tagId: Long,
    val color: Int,
    val name: String
)