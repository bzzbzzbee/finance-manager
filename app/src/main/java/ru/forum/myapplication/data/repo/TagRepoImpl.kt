package ru.forum.myapplication.data.repo

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import ru.forum.myapplication.data.mappers.TagMapper.toTag
import ru.forum.myapplication.data.mappers.TagMapper.toTagDbModel
import ru.forum.myapplication.data.room.TagDao
import ru.forum.myapplication.domain.entity.Tag
import ru.forum.myapplication.domain.usecases.tags.TagRepo
import javax.inject.Inject

class TagRepoImpl @Inject constructor(
    private val tagDao: TagDao
) : TagRepo {
    override suspend fun getTags(): Flow<Resource<List<Tag>>> =
        tagDao.getTags().map { Resource.Success(it.map { it.toTag() }) }


    override suspend fun addTag(tag: Tag): Flow<Resource<Boolean>> {
        return try {
            tagDao.insertTag(tag.toTagDbModel())
            flowOf(Resource.Success(true))
        } catch (t: Throwable) {
            flowOf(Resource.Error(t))
        }
    }

    override suspend fun removeTag(tag: Tag): Flow<Resource<Boolean>> {
        return try {
            tagDao.deleteTag(tag.toTagDbModel())
            flowOf(Resource.Success(true))
        } catch (t: Throwable) {
            flowOf(Resource.Error(t))
        }
    }
}