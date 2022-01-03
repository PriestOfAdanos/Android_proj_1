package com.plcoding.multipleroomtables.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Grade(
    @PrimaryKey(autoGenerate = true) val gradeID: Int=0,
    val grade: Float,
    val scaleEquation: Float,
    val weight: Float,
    val subjectName: String,
    val studentIndex: Int
)