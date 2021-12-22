package com.plcoding.multipleroomtables.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Grade(
    @PrimaryKey(autoGenerate = true)
    val gradeID: Int,
    val grade: Int,
    val subjectName: String,
    val studentID: Int
)