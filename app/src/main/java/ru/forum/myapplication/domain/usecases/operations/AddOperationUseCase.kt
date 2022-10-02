package ru.forum.myapplication.domain.usecases.operations

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import ru.forum.myapplication.data.repo.Resource
import ru.forum.myapplication.domain.entity.Operation
import javax.inject.Inject

class AddOperationUseCase @Inject constructor(private val repository: OperationsRepo) {
    suspend operator fun invoke(operation: Operation): Flow<Resource<Boolean>> =
        repository.addOperation(operation).flowOn(Dispatchers.IO)
}