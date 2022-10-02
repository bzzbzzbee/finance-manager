package ru.forum.myapplication.data.repo

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import ru.forum.myapplication.data.mappers.OperationMapper.toOperation
import ru.forum.myapplication.data.mappers.OperationMapper.toOperationDbModel
import ru.forum.myapplication.data.mappers.TagMapper.toTag
import ru.forum.myapplication.data.mappers.TagMapper.toTagDbModel
import ru.forum.myapplication.data.room.OperationsDao
import ru.forum.myapplication.data.room.TagDao
import ru.forum.myapplication.data.room.TagOperationCrossRef
import ru.forum.myapplication.domain.entity.Operation
import ru.forum.myapplication.domain.usecases.operations.OperationsRepo
import javax.inject.Inject

class OperationRepoImpl @Inject constructor(
    private val operationsDao: OperationsDao,
    private val tagDao: TagDao
) : OperationsRepo {
    override suspend fun getOperations(): Flow<Resource<List<Operation>>> {
        val operationsWithTags = operationsDao.getOperationsWithTags()
        val operations =
            operationsWithTags.map { list -> list.map { it.operation.toOperation(it.tags.map { it.toTag() }) } }
        return operations.map { Resource.Success(it) }
    }

    override suspend fun addOperation(operation: Operation): Flow<Resource<Boolean>> {
        return try {
            val tags = operation.tags.map { it.toTagDbModel() }
            val operationId = operationsDao.insertOperation(operation.toOperationDbModel())

            tags.forEach { tag ->
                val tagId = tagDao.insertTag(tag)
                val crossRef = TagOperationCrossRef(tagId, operationId)
                operationsDao.insertCrossRef(crossRef)
            }
            flowOf(Resource.Success(true))
        } catch (t: Throwable) {
            flowOf(Resource.Error(t))
        }
    }

    override suspend fun removeOperation(operation: Operation): Flow<Resource<Boolean>> {
        return try {
            operationsDao.deleteOperation(operation.toOperationDbModel())
            flowOf(Resource.Success(true))
        } catch (t: Throwable) {
            flowOf(Resource.Error(t))
        }
    }
}