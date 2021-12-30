package com.plcoding.multipleroomtables.Student

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.plcoding.multipleroomtables.R
import com.plcoding.multipleroomtables.entities.Student
import androidx.lifecycle.Observer

class StudentListFragment: Fragment() {

    lateinit var viewModel: StudentListViewModel
    companion object {
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_student_list,container,false)
    }

    override  fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var subjectName = arguments?.getString("subjectName") ?: "HWDP"

        val factory= StudentListViewModelFactory((requireNotNull(this.activity).application),subjectName)
        viewModel=ViewModelProvider(requireActivity(),factory).
        get(StudentListViewModel::class.java)
        Log.d("sName", subjectName)
        //viewModel.studentsOfSubject(subjectName)

        val studentListAdapter= StudentListAdapter(viewModel.students,viewModel.allStudents,viewModel,subjectName)
        viewModel.allStudents.observe(viewLifecycleOwner,
            Observer<List<Student>> { studentListAdapter.notifyDataSetChanged() }
        )
        viewModel.students.observe(viewLifecycleOwner,
            Observer<List<Student>> { studentListAdapter.notifyDataSetChanged() }
        )

        val layoutManager=LinearLayoutManager(view.context)
        view.findViewById<RecyclerView>(R.id.student_recyclerView).let {
            it.adapter=studentListAdapter
            it.layoutManager=layoutManager
        }
        view.findViewById<Button>(R.id.button_back_to_form).apply {
            setOnClickListener {
                it.findNavController().navigate(R.id.action_studentListFragment_to_subjectListFragment)
            }
        }
    }
}