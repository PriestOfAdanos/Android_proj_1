package com.plcoding.multipleroomtables.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.plcoding.multipleroomtables.entities.Grade
import com.plcoding.multipleroomtables.entities.Subject

data class SubjectWithGrades(
    @Embedded val subject: Subject,
    @Relation(
        parentColumn = "subjectName",
        entityColumn = "subjectName"
    )
    val grades: List<Grade>
)