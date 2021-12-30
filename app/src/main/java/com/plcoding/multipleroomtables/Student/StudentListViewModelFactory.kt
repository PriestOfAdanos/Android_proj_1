package com.plcoding.multipleroomtables.Student

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.plcoding.multipleroomtables.entities.Subject

class StudentListViewModelFactory(
    private val application: Application, private val subjectName: String
) :ViewModelProvider.Factory {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StudentListViewModel::class.java)) {
            return StudentListViewModel(application, subjectName) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }


}