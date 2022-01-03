package com.plcoding.multipleroomtables.Grade

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.plcoding.multipleroomtables.entities.Subject

class GradeListViewModelFactory(
    private val application: Application, val studentIndex: Int, val subjectName: String
) :ViewModelProvider.Factory {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GradeListViewModel::class.java)) {
            return GradeListViewModel(application,studentIndex,subjectName) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }


}