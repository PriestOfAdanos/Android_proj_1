package com.plcoding.multipleroomtables.entities.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.plcoding.multipleroomtables.entities.Grade
import com.plcoding.multipleroomtables.entities.Student
import com.plcoding.multipleroomtables.entities.Subject

data class StudentWithGrades(
    @Embedded val student: Student,
    @Relation(
        parentColumn = "studentIndex",
        entityColumn = "studentIndex"
    )
    val grades: List<Grade>
)