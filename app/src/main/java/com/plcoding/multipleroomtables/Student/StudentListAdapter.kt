package com.plcoding.multipleroomtables.Student

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.plcoding.multipleroomtables.R
import com.plcoding.multipleroomtables.entities.Student

class StudentListAdapter(private val students: List<Student>, private val viewModel: StudentListViewModel)
    :RecyclerView.Adapter<StudentListAdapter.StudentListHolder>()
{
    inner class StudentListHolder(private val view: View): RecyclerView.ViewHolder(view)
    {
        val textViewStudentIndex=view.findViewById<TextView>(R.id.student_index)
        val textViewStudentName=view.findViewById<TextView>(R.id.student_name)
        val buttonDelete=view.findViewById<Button>(R.id.button_delete_student)
        val buttonDetails=view.findViewById<Button>(R.id.button_student_details)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentListHolder {
        val view=LayoutInflater.from(parent.context).
                inflate(R.layout.one_student_row,parent,false)
        return StudentListHolder(view)
    }

    override fun onBindViewHolder(holder: StudentListHolder, position: Int) {
        holder.textViewStudentIndex.text=students.get(position)?.studentIndex.toString()
        holder.textViewStudentName.text=students.get(position)?.studentName
        holder.buttonDelete.setOnClickListener {
            students.let{ existingStudent->
                viewModel.deleteStudent(existingStudent.get(position))
            }
        }



    }

    override fun getItemCount()=students.size?:0

}