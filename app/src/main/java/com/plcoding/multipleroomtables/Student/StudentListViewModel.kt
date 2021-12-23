package com.plcoding.multipleroomtables.Student

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.plcoding.multipleroomtables.SchoolDao
import com.plcoding.multipleroomtables.SchoolDatabase
import com.plcoding.multipleroomtables.entities.Student
import com.plcoding.multipleroomtables.entities.Subject
import com.plcoding.multipleroomtables.entities.relations.SubjectWithStudents
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StudentListViewModel(
    application: Application):
          AndroidViewModel(application) {
    private val schoolDAO: SchoolDao = SchoolDatabase.getInstance(application).schoolDao
    private val subjectsWithStudents: LiveData<List<SubjectWithStudents>> = schoolDAO.getStudentsOfSubject("abc")
    val students = subjectsWithStudents.value?.get(0)?.students ?: listOf<Student>()


    fun deleteStudent(student: Student)
    {
        viewModelScope.launch(Dispatchers.IO) {
            schoolDAO.deleteStudent(student)
        }
    }
}