package ru.forum.myapplication.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.forum.myapplication.data.repo.OperationRepoImpl
import ru.forum.myapplication.data.repo.TagRepoImpl
import ru.forum.myapplication.data.room.OperationsDao
import ru.forum.myapplication.data.room.TagDao
import ru.forum.myapplication.domain.usecases.operations.OperationsRepo
import ru.forum.myapplication.domain.usecases.tags.TagRepo

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun provideOperationsRepo(
        operationsDao: OperationsDao,
        tagDao: TagDao
    ): OperationsRepo =
        OperationRepoImpl(operationsDao, tagDao)

    @Provides
    fun provideTagRepo(
        tagDao: TagDao
    ): TagRepo =
        TagRepoImpl(tagDao)
}