package ru.forum.myapplication.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.forum.myapplication.data.models.OperationDbModel
import ru.forum.myapplication.data.models.TagDbModel

@Database(
    entities = [OperationDbModel::class, TagDbModel::class, TagOperationCrossRef::class],
    version = 1,
    exportSchema = false
)
abstract class AppDataBase : RoomDatabase() {
    abstract fun operationsDao(): OperationsDao
    abstract fun tagDao(): TagDao
}