package com.plcoding.multipleroomtables.Grade

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.plcoding.multipleroomtables.R
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.plcoding.multipleroomtables.Student.StudentListFragmentDirections
import com.plcoding.multipleroomtables.entities.Grade

class GradeListFragment: Fragment() {
    private lateinit var gradeListAdapter: GradeListAdapter
    val args: GradeListFragmentArgs by navArgs()




    override fun onResume() {
        super.onResume()
        var subjectName = args.subjectName
        var studentIndex = args.studentIndex

        val factory= GradeListViewModelFactory((requireNotNull(this.activity).application), subjectName = subjectName, studentIndex = studentIndex)
        var viewModel=ViewModelProvider(requireActivity(),factory).
        get(GradeListViewModel::class.java)
        viewModel.updateGradeList(subjectName = subjectName, studentIndex = studentIndex)
        viewModel.computeAverage(subjectName = subjectName, studentIndex = studentIndex)
        gradeListAdapter= GradeListAdapter(viewModel.grades,viewModel)

        viewModel.grades.observe(viewLifecycleOwner,
            Observer<List<Grade>> { gradeListAdapter.notifyDataSetChanged() }
        )

        val layoutManager=LinearLayoutManager(view?.context)
        view?.findViewById<RecyclerView>(R.id.grade_recyclerView).let {
            it?.adapter=gradeListAdapter
            it?.layoutManager=layoutManager
        }
        val textGradeAverage=viewModel.average
        val textHolder = view?.findViewById<TextView>(R.id.average_number)
        textHolder?.text = textGradeAverage.toString()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_grade_list,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        view.findViewById<Button>(R.id.button_grade_to_subject_list)?.apply {
            setOnClickListener {
                val action = GradeListFragmentDirections.actionGradeListFragmentToStudentListFragment(args.subjectName)
                it.findNavController().navigate(action)
            }
        }
        view.findViewById<Button>(R.id.button_grade_list_to_grade)?.apply {
            setOnClickListener {
                val action = GradeListFragmentDirections.actionGradeListFragmentToGradeFragment(args.studentIndex,args.subjectName)
                it.findNavController().navigate(action)
            }
        }
    }

}