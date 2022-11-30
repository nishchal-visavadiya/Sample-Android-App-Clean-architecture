package com.core.domain

import java.util.*

data class Task(
    val id: String,
    val title: String = "",
    val description: String = "",
    val completionStatus: CompletionStatus,
    val isDeleted: Boolean
) {
    sealed class CompletionStatus {

        data class Complete(val completionDate: Date): CompletionStatus()

        object InComplete: CompletionStatus()
    }
}