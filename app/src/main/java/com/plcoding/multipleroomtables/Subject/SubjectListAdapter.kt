package com.plcoding.multipleroomtables.Subject

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.LiveData
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.plcoding.multipleroomtables.R
import com.plcoding.multipleroomtables.entities.Subject

class SubjectListAdapter(private val subjects: LiveData<List<Subject>>, private val viewModel: SubjectListViewModel)
    :RecyclerView.Adapter<SubjectListAdapter.SubjectListHolder>()
{
    inner class SubjectListHolder(private val view: View): RecyclerView.ViewHolder(view)
    {
        val textViewSubjectName=view.findViewById<TextView>(R.id.subject_name)
        val textViewLSubjectStart=view.findViewById<TextView>(R.id.subject_start)
        val textViewSubjectDuration=view.findViewById<TextView>(R.id.subject_duration)
        val buttonDelete=view.findViewById<Button>(R.id.button_delete_subject)
        val buttonDetails=view.findViewById<Button>(R.id.button_subject_details)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectListHolder {
        val view=LayoutInflater.from(parent.context).
                inflate(R.layout.one_subject_row,parent,false)
        return SubjectListHolder(view)
    }

    override fun onBindViewHolder(holder: SubjectListHolder, position: Int) {
        holder.textViewSubjectName.text=subjects.value?.get(position)?.subjectName
        holder.textViewLSubjectStart.text=subjects.value?.get(position)?.start
        holder.textViewSubjectDuration.text=subjects.value?.get(position)?.duration

        holder.buttonDelete.setOnClickListener {
            subjects.value?.let{ existingSubject->
                viewModel.deleteSubject(existingSubject.get(position))
            }
        }
        holder.buttonDetails.setOnClickListener {
            subjects.value?.let{ existingSubject->
                val bundle = bundleOf("amount" to existingSubject)
                it.findNavController().navigate(R.id.action_subjectListFragment_to_studentListFragment,bundle)
            }
        }



    }

    override fun getItemCount()=subjects.value?.size?:0

}