package com.plcoding.multipleroomtables.Student

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class AddStudentViewModelFactory(
    private val application: Application

): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddStudentViewModel::class.java)) {
            return AddStudentViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}