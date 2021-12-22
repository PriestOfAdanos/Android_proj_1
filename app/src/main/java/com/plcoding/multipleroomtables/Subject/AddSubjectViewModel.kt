package com.plcoding.multipleroomtables.Subject

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.multipleroomtables.SchoolDao
import com.plcoding.multipleroomtables.SchoolDatabase
import com.plcoding.multipleroomtables.entities.Subject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class AddSubjectViewModel(application: Application): AndroidViewModel(application) {

          private val schoolDAO : SchoolDao
          init{
              schoolDAO= SchoolDatabase.getInstance(application).schoolDao
          }
           fun addSubject(subject: Subject) {
               //Toast.makeText(getApplication(),user.toString(),Toast.LENGTH_LONG).show()
               viewModelScope.launch(Dispatchers.IO){
                  schoolDAO.insertSubject(subject)
             }
        }

}