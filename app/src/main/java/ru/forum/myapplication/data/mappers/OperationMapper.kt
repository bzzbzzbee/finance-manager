package ru.forum.myapplication.data.mappers

import ru.forum.myapplication.data.models.OperationDbModel
import ru.forum.myapplication.domain.entity.Operation
import ru.forum.myapplication.domain.entity.Tag

object OperationMapper {
    fun OperationDbModel.toOperation(tags: List<Tag>): Operation =
        Operation(
            this.operationId,
            this.amount,
            this.comment,
            this.date,
            tags
        )

    fun Operation.toOperationDbModel(): OperationDbModel =
        OperationDbModel(
            this.operationId,
            this.amount,
            this.comment,
            this.date
        )
}