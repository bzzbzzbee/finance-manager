package ru.forum.myapplication.data.room

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import ru.forum.myapplication.CONST.OPERATION_TABLE
import ru.forum.myapplication.data.models.OperationDbModel

@Dao
interface OperationsDao {
    @Query("SELECT * FROM $OPERATION_TABLE")
    fun getOperations(): Flow<List<OperationDbModel>>

    @Transaction
    @Query("SELECT * FROM $OPERATION_TABLE")
    fun getOperationsWithTags(): Flow<List<OperationWithTags>>

    @Transaction
    @Query("SELECT * FROM $OPERATION_TABLE WHERE operationId=:id")
    fun getOperationWithTags(id: Long): Flow<OperationWithTags>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOperation(operation: OperationDbModel): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOperations(operations: List<OperationDbModel>): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCrossRef(crossRef: TagOperationCrossRef)

    @Delete
    suspend fun deleteOperation(operation: OperationDbModel)
}