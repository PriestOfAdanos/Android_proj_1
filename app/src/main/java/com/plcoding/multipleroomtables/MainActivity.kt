package com.plcoding.multipleroomtables

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
//import androidx.lifecycle.lifecycleScope
import com.plcoding.multipleroomtables.entities.Student
import com.plcoding.multipleroomtables.entities.Subject
import com.plcoding.multipleroomtables.entities.relations.StudentSubjectCrossRef
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dao = SchoolDatabase.getInstance(this).schoolDao

        val subjects = listOf(
            Subject("Dating for programmers","friday","1h"),
            Subject("Avoiding depression","friday","1h"),
            Subject("Bug Fix Meditation","friday","1h"),
            Subject("Logcat for Newbies","friday","1h"),
            Subject("How to use Google","friday","1h")
        )

        val students = listOf(
            Student(123,"Beff Jezos"),
            Student(321,"Mark Suckerberg"),
        )

        val studentSubjectRelations = listOf(
            StudentSubjectCrossRef(123, "Dating for programmers"),
            StudentSubjectCrossRef(123, "Avoiding depression"),
        )

//        lifecycleScope.launch {
//            subjects.forEach { dao.insertSubject(it) }
//            students.forEach { dao.insertStudent(it) }
//            studentSubjectRelations.forEach { dao.insertStudentSubjectCrossRef(it) }
//        }
    }
}