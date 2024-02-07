package com.capgemini.tasktracker.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TaskTrackerDAO {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertUser(user: User)

    @Update
    fun updateUser(user: User)

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertTask(task: Task)

    @Update
    fun updateTask(task: Task)

    @Delete
    fun deleteTask(task: Task)

    @Query("DELETE FROM Task")
    fun deleteAllTasks()

    @Query("SELECT * FROM Task ORDER BY end_date ASC")
    fun getAllTasks(): LiveData<List<Task>>

    @Query("SELECT * FROM Task WHERE task_name LIKE :task")
    fun searchTask(searchQuery: String): LiveData<List<Task>>
}