package com.plcoding.multipleroomtables.Student

import android.app.Application
import android.util.Log
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
    var students = schoolDAO.getAllStudents()

    fun getStundents(subjectName: String){
        students = schoolDAO.getStudentNamesBySubjectName(subjectName)
    }
//    fun updateStudents(subjectName: String){
//        students = schoolDAO.getStudentNamesBySubjectName(subjectName)
//    }
    fun deleteStudent(student: Student)
    {
        viewModelScope.launch(Dispatchers.IO) {
            schoolDAO.deleteStudent(student)
        }
    }

    fun addStudentToSubject(student: Student, subjectName: String)
    {
        viewModelScope.launch(Dispatchers.IO) {
            schoolDAO.deleteStudent(student)
        }
    }
    fun removeStudentFromSubject(student: Student, subjectName: String)
    {
        viewModelScope.launch(Dispatchers.IO) {
            schoolDAO.deleteStudent(student)
        }
    }

}