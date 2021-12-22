package com.plcoding.multipleroomtables.entities.relations

import androidx.room.Entity

@Entity(primaryKeys = ["studentIndex", "subjectName"])
data class StudentSubjectCrossRef(
    val studentIndex: Int,
    val subjectName: String
)