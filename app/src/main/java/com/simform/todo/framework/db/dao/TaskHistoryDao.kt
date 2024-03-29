package com.simform.todo.framework.db.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.Companion.REPLACE
import com.simform.todo.framework.db.entity.TaskEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskHistoryDao {

    @Insert(onConflict = REPLACE)
    suspend fun addTask(task: TaskEntity)

    @Delete
    suspend fun deleteTask(task: TaskEntity)

    @Update
    suspend fun updateTask(task: TaskEntity)

    @Query("SELECT * from task_history where id = :id")
    suspend fun getTaskById(id: String): TaskEntity

    @Query("SELECT * from task_history WHERE isInTheBin is 0")
    fun getTasksAsFlow(): Flow<List<TaskEntity>>

    @Query("SELECT * from task_history WHERE isInTheBin is 1")
    fun getBinItemsAsFlow(): Flow<List<TaskEntity>>

    @Query("SELECT * from task_history")
    fun getAllTasksAsFlow(): Flow<List<TaskEntity>>
}