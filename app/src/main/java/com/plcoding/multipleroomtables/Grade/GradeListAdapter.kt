package com.plcoding.multipleroomtables.Grade

import android.graphics.Color
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
import com.plcoding.multipleroomtables.entities.Grade
import com.plcoding.multipleroomtables.entities.Student

class GradeListAdapter(private val grades: LiveData<List<Grade>>, private val viewModel: GradeListViewModel)
    :RecyclerView.Adapter<GradeListAdapter.GradeListHolder>()
{
    inner class GradeListHolder(private val view: View): RecyclerView.ViewHolder(view)
    {
        val textViewGradeValue=view.findViewById<TextView>(R.id.grade_value_view)
        val textViewGradeEquation=view.findViewById<TextView>(R.id.grade_equation_view)
        val textViewGradeWeight=view.findViewById<TextView>(R.id.grade_weight_view)

        val buttonDelete = view.findViewById<Button>(R.id.button_delete_grade)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GradeListHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.one_grade_row,parent,false)
        return GradeListHolder(view)
    }
    override fun onBindViewHolder(holder: GradeListHolder, position: Int) {
        holder.textViewGradeValue.text = grades.value?.get(position)?.grade.toString()
        holder.textViewGradeEquation.text = grades.value?.get(position)?.scaleEquation.toString()
        holder.textViewGradeWeight.text = grades.value?.get(position)?.scaleEquation.toString()

        holder.buttonDelete.setOnClickListener {
            grades.value?.let{ grade->
                viewModel.deleteGrade(grade.get(position))
            }
        }

    }
    override fun getItemCount()=grades.value?.size?:0
}