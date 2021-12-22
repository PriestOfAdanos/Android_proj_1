package com.plcoding.multipleroomtables.Subject

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class SubjectListViewModelFactory(
     private val application: Application
) :ViewModelProvider.Factory {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SubjectListViewModel::class.java)) {
            return SubjectListViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }


}