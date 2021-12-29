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
    application: Application, subjectName: String?):
          AndroidViewModel(application) {
    private val schoolDAO: SchoolDao = SchoolDatabase.getInstance(application).schoolDao


    //val students:LiveData<List<Student>> = schoolDAO.getAllStudents()

    //val students:LiveData<List<Student>> = schoolDAO.getAllStudents()

    var students: LiveData<List<Student>> = if(subjectName!= null) schoolDAO.getStudentNamesBySubjectName(subjectName) else schoolDAO.getAllStudents()


    //val students = subjectsWithStudents?.value?.get(0)?.students ?: listOf()


    fun deleteStudent(student: Student)
    {
        viewModelScope.launch(Dispatchers.IO) {
            schoolDAO.deleteStudent(student)
        }
    }
}