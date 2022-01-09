package com.plcoding.multipleroomtables.Subject

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.plcoding.multipleroomtables.SchoolDao
import com.plcoding.multipleroomtables.SchoolDatabase
import com.plcoding.multipleroomtables.entities.Subject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SubjectListViewModel(
    application: Application):
          AndroidViewModel(application) {

    private val sclDB : SchoolDatabase = SchoolDatabase.getInstance(application)
    private val schoolDAO: SchoolDao = sclDB.schoolDao
    val subjects:LiveData<List<Subject>> = schoolDAO.getAllSubjects()

    fun deleteSubject(subject: Subject)
    {
        viewModelScope.launch(Dispatchers.IO) {
            schoolDAO.deleteSubject(subject)
        }
    }

    fun deleteAll(){
        sclDB.clearAllTables()
    }
}