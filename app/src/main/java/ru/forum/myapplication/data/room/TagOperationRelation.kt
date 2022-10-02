package ru.forum.myapplication.data.room

import androidx.room.*
import ru.forum.myapplication.CONST.OPERATION_TAG_CROSSREF_TABLE
import ru.forum.myapplication.data.models.OperationDbModel
import ru.forum.myapplication.data.models.TagDbModel

data class OperationWithTags(
    @Embedded val operation: OperationDbModel,
    @Relation(
        parentColumn = "operationId",
        entityColumn = "tagId",
        associateBy = Junction(TagOperationCrossRef::class)
    )
    val tags: List<TagDbModel>
)

data class TagWithOperations(
    @Embedded val tag: TagDbModel,
    @Relation(
        parentColumn = "tagId",
        entityColumn = "operationId",
        associateBy = Junction(TagOperationCrossRef::class)
    )
    val operations: List<OperationDbModel>
)

@Entity(
    tableName = OPERATION_TAG_CROSSREF_TABLE,
    primaryKeys = ["tagId", "operationId"],
    foreignKeys = [
        ForeignKey(
            entity = OperationDbModel::class,
            parentColumns = ["operationId"],
            childColumns = ["tagId"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = TagDbModel::class,
            parentColumns = ["tagId"],
            childColumns = ["operationId"],
            onUpdate = ForeignKey.CASCADE
        )],
    indices = [Index("tagId"), Index("operationId")]
)
data class TagOperationCrossRef(
    val tagId: Long,
    val operationId: Long
)