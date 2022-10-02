package ru.forum.myapplication.data.mappers

import ru.forum.myapplication.data.models.TagDbModel
import ru.forum.myapplication.domain.entity.Tag

object TagMapper {
    fun TagDbModel.toTag(): Tag =
        Tag(
            this.tagId,
            this.color,
            this.name
        )

    fun Tag.toTagDbModel(): TagDbModel =
        TagDbModel(
            this.tagId,
            this.color,
            this.name
        )
}