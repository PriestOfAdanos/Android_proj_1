package com.plcoding.multipleroomtables.Student

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.lifecycle.LiveData
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.plcoding.multipleroomtables.R
import com.plcoding.multipleroomtables.entities.Student

class StudentListAdapter(private val students: LiveData<List<Student>>,private val allStudents: LiveData<List<Student>>, private val viewModel: StudentListViewModel, private val subjectName:String)
    :RecyclerView.Adapter<StudentListAdapter.StudentListHolder>()
{
    inner class StudentListHolder(private val view: View): RecyclerView.ViewHolder(view)
    {
        val textViewStudentIndex=view.findViewById<TextView>(R.id.student_index)
        val textViewStudentName=view.findViewById<TextView>(R.id.student_name)
        val buttonAction=view.findViewById<Button>(R.id.button_action_student)
        val buttonDetails=view.findViewById<Button>(R.id.button_student_details)


    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentListHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.one_student_row,parent,false)
        return StudentListHolder(view)
    }
    override fun onBindViewHolder(holder: StudentListHolder, position: Int) {
        holder.textViewStudentIndex.text = students.value?.get(position)?.studentIndex.toString()
        holder.textViewStudentName.text = students.value?.get(position)?.studentName

        holder.buttonAction.setOnClickListener {
            students.value?.let{ existingStudent->
                viewModel.addStudentToSubject(existingStudent.get(position),subjectName)
            }
        }

    }
    override fun getItemCount()=students.value?.size?:0
}