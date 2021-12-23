package com.plcoding.multipleroomtables.Student

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.multipleroomtables.SchoolDao
import com.plcoding.multipleroomtables.SchoolDatabase
import com.plcoding.multipleroomtables.entities.Student
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class AddStudentViewModel(application: Application): AndroidViewModel(application) {

          private val schoolDAO : SchoolDao
          init{
              schoolDAO= SchoolDatabase.getInstance(application).schoolDao
          }
           fun addStudent(student: Student) {
               //Toast.makeText(getApplication(),user.toString(),Toast.LENGTH_LONG).show()
               viewModelScope.launch(Dispatchers.IO){
                  schoolDAO.insertStudent(student)
             }
        }

}