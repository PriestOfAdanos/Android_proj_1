package com.plcoding.multipleroomtables.Grade

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class AddGradeViewModelFactory(
    private val application: Application

): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddGradeViewModel::class.java)) {
            return AddGradeViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}