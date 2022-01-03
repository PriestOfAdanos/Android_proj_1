package com.plcoding.multipleroomtables.Grade

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.multipleroomtables.SchoolDao
import com.plcoding.multipleroomtables.SchoolDatabase
import com.plcoding.multipleroomtables.entities.Grade
import com.plcoding.multipleroomtables.entities.Student
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class AddGradeViewModel(application: Application): AndroidViewModel(application) {

          private val schoolDAO : SchoolDao
          init{
              schoolDAO= SchoolDatabase.getInstance(application).schoolDao
          }
           fun addGrade(grade: Grade) {
               viewModelScope.launch(Dispatchers.IO){
                  schoolDAO.insertGrade(grade)
             }
        }

}