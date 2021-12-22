package com.plcoding.multipleroomtables.Subject

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class AddSubjectViewModelFactory(
    private val application: Application

): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddSubjectViewModel::class.java)) {
            return AddSubjectViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}