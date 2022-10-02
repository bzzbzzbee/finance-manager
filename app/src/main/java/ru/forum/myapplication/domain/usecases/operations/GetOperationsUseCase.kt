package ru.forum.myapplication.domain.usecases.operations

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import ru.forum.myapplication.data.repo.Resource
import ru.forum.myapplication.domain.entity.Operation
import javax.inject.Inject

class GetOperationsUseCase @Inject constructor(private val repository: OperationsRepo) {
    suspend operator fun invoke(): Flow<Resource<List<Operation>>> =
        repository.getOperations().flowOn(Dispatchers.IO)
}