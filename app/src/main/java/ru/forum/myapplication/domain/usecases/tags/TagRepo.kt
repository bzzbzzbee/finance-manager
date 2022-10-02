package ru.forum.myapplication.domain.usecases.tags

import kotlinx.coroutines.flow.Flow
import ru.forum.myapplication.data.repo.Resource
import ru.forum.myapplication.domain.entity.Tag

interface TagRepo {
    suspend fun getTags(): Flow<Resource<List<Tag>>>
    suspend fun addTag(tag: Tag): Flow<Resource<Boolean>>
    suspend fun removeTag(tag: Tag): Flow<Resource<Boolean>>
}