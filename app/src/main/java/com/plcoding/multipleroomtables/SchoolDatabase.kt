package com.plcoding.multipleroomtables

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.plcoding.multipleroomtables.entities.Student
import com.plcoding.multipleroomtables.entities.Grade
import com.plcoding.multipleroomtables.entities.Subject
import com.plcoding.multipleroomtables.entities.relations.StudentSubjectCrossRef

@Database(
    entities = [
        Student::class,
        Subject::class,
        StudentSubjectCrossRef::class,
        Grade::class
    ],
    version = 1
)
abstract class SchoolDatabase : RoomDatabase() {

    abstract val schoolDao: SchoolDao

    companion object {
        @Volatile
        private var INSTANCE: SchoolDatabase? = null

        fun getInstance(context: Context): SchoolDatabase {
            synchronized(this) {
                return INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    SchoolDatabase::class.java,
                    "school_db"
                ).allowMainThreadQueries().build().also {
                    INSTANCE = it
                }
            }
        }
    }
}