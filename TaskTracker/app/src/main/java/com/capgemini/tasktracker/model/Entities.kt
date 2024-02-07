package com.capgemini.tasktracker.model

import androidx.room.ColumnInfo
import java.sql.Date

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName="User")
data class User(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("user_id")
    val userId: Int,

    @ColumnInfo("user_email")
    val email: String,

    @ColumnInfo("user_password")
    val password: String
)

@Entity(tableName = "Task", foreignKeys = [ForeignKey(entity = User::class, childColumns = ["user_id"], parentColumns = ["task_id"])])
data class Task(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("task_name")
    val taskName: String,

    @ColumnInfo("user_id")
    val userId: Int,

    @ColumnInfo("task_start_date")
    var startDate: Date,

    @ColumnInfo("task_end_date")
    var endDate: Date,

    @ColumnInfo("task_priority")
    var priority: String,

    @ColumnInfo("task_status")
    var status: Boolean,

    @ColumnInfo("task_description")
    var Description: String                    //later change it to checkpoints
)
