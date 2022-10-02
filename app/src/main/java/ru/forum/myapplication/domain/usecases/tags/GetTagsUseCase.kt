package ru.forum.myapplication.domain.usecases.tags

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import ru.forum.myapplication.data.repo.Resource
import ru.forum.myapplication.domain.entity.Tag
import javax.inject.Inject

class GetTagsUseCase @Inject constructor(private val repository: TagRepo) {
    suspend operator fun invoke(): Flow<Resource<List<Tag>>> =
        repository.getTags().flowOn(Dispatchers.IO)
}