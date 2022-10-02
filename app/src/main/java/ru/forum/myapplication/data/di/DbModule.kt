package ru.forum.myapplication.data.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.forum.myapplication.CONST.DB_NAME
import ru.forum.myapplication.data.room.AppDataBase
import ru.forum.myapplication.data.room.OperationsDao
import ru.forum.myapplication.data.room.TagDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DbModule {
    @Provides
    @Singleton
    fun provideAppDb(@ApplicationContext applicationContext: Context): AppDataBase =
        Room.databaseBuilder(
            applicationContext,
            AppDataBase::class.java, DB_NAME
        ).build()

    @Provides
    fun provideOperationsDao(db: AppDataBase): OperationsDao = db.operationsDao()

    @Provides
    fun provideTagDao(db: AppDataBase): TagDao = db.tagDao()
}