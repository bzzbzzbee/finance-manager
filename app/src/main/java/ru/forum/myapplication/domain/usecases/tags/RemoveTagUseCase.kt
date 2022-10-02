package ru.forum.myapplication.domain.usecases.tags

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import ru.forum.myapplication.data.repo.Resource
import ru.forum.myapplication.domain.entity.Tag
import javax.inject.Inject

class RemoveTagUseCase @Inject constructor(private val repository: TagRepo) {
    suspend operator fun invoke(tag: Tag): Flow<Resource<Boolean>> =
        repository.removeTag(tag).flowOn(Dispatchers.IO)
}