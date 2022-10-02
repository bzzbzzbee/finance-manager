package ru.forum.myapplication.data.room

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import ru.forum.myapplication.CONST.TAG_TABLE
import ru.forum.myapplication.data.models.TagDbModel

@Dao
interface TagDao {
    @Query("SELECT * FROM $TAG_TABLE")
    fun getTags(): Flow<List<TagDbModel>>

    @Transaction
    @Query("SELECT * FROM $TAG_TABLE")
    fun getTagsWithOperations(): Flow<List<TagWithOperations>>

    @Transaction
    @Query("SELECT * FROM $TAG_TABLE WHERE tagId=:id")
    fun getTagWithOperations(id: Long): Flow<TagWithOperations>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTag(tag: TagDbModel): Long

    @Delete
    suspend fun deleteTag(tag: TagDbModel)
}