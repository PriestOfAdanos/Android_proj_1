package com.plcoding.multipleroomtables.Subject

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.plcoding.multipleroomtables.R
import com.plcoding.multipleroomtables.entities.Subject

class SubjectListFragment: Fragment() {

    lateinit var viewModel: SubjectListViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        return inflater.inflate(R.layout.fragment_subject_list,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory= SubjectListViewModelFactory((requireNotNull(this.activity).application))
        viewModel=ViewModelProvider(requireActivity(),factory).
        get(SubjectListViewModel::class.java)


        val subjectListAdapter= SubjectListAdapter(viewModel.subjects,viewModel)

        viewModel.subjects.observe(viewLifecycleOwner,
            Observer<List<Subject>> { subjectListAdapter.notifyDataSetChanged() }
        )


        val layoutManager=LinearLayoutManager(view.context)
        view.findViewById<RecyclerView>(R.id.subject_recyclerView).let {

            it.adapter=subjectListAdapter
            it.layoutManager=layoutManager
        }
        view.findViewById<Button>(R.id.button_subject_to_student).apply {
            setOnClickListener {
                it.findNavController().navigate(R.id.action_subjectListFragment_to_studentFragment)
            }
        }
        view.findViewById<Button>(R.id.button_back_to_form).apply {
            setOnClickListener {
                it.findNavController().navigate(R.id.action_subjectListFragment_to_subjectFragment)
            }
        }


    }

}