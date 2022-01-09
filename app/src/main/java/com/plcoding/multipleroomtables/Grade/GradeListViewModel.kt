package com.plcoding.multipleroomtables.Grade

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.plcoding.multipleroomtables.SchoolDao
import com.plcoding.multipleroomtables.SchoolDatabase
import com.plcoding.multipleroomtables.entities.Grade
import com.plcoding.multipleroomtables.entities.Student
import com.plcoding.multipleroomtables.entities.Subject
import com.plcoding.multipleroomtables.entities.relations.StudentSubjectCrossRef
import com.plcoding.multipleroomtables.entities.relations.SubjectWithStudents
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GradeListViewModel(
    application: Application, studentIndex: Int, subjectName: String):
          AndroidViewModel(application) {
    private val schoolDAO: SchoolDao = SchoolDatabase.getInstance(application).schoolDao

    var grades= schoolDAO.getGradesByStudentIndexAndSubjectName(studentIndex,subjectName)
    var average= schoolDAO.getGradesAverageOfStudent(studentIndex,subjectName)


    fun updateGradeList(studentIndex:Int,subjectName:String){
        grades= schoolDAO.getGradesByStudentIndexAndSubjectName(studentIndex,subjectName)
    }

    fun deleteGrade(grade: Grade)
    {
        viewModelScope.launch(Dispatchers.IO) {
            schoolDAO.deleteGrade(grade)
        }
    }

    fun computeAverage(studentIndex:Int,subjectName:String) {
            average= schoolDAO.getGradesAverageOfStudent(studentIndex,subjectName)
    }

    fun addGradeToStudentAndSubject(grade: Grade)
    {
        viewModelScope.launch(Dispatchers.IO) {
            schoolDAO.insertGrade(grade)
        }
    }
}