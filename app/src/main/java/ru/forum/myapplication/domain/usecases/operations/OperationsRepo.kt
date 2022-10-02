package ru.forum.myapplication.domain.usecases.operations

import kotlinx.coroutines.flow.Flow
import ru.forum.myapplication.data.repo.Resource
import ru.forum.myapplication.domain.entity.Operation

interface OperationsRepo {
    suspend fun getOperations(): Flow<Resource<List<Operation>>>
    suspend fun addOperation(operation: Operation): Flow<Resource<Boolean>>
    suspend fun removeOperation(operation: Operation): Flow<Resource<Boolean>>
}
