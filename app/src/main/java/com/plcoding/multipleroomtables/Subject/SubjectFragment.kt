package com.plcoding.multipleroomtables.Subject

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.plcoding.multipleroomtables.R
import com.plcoding.multipleroomtables.entities.Subject

class SubjectFragment: Fragment() {


    private lateinit var viewModel: AddSubjectViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
      //val factory=AddUserViewModelFactory(requireActivity().application)
      viewModel=ViewModelProvider(requireActivity()).get(AddSubjectViewModel::class.java)
        return inflater.inflate(R.layout.fragment_subject,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val name = view.findViewById<EditText>(R.id.subject_name)
        val start = view.findViewById<EditText>(R.id.subject_start)
        val duration = view.findViewById<EditText>(R.id.subject_duration)

        view.findViewById<Button>(R.id.button_add_subject).apply {
            setOnClickListener {
                val subjectName = name.text.toString()
                val subjectStart = start.text.toString()
                val subjectDuration= duration.text.toString()
                val subject= Subject(subjectName,subjectStart,subjectDuration)
                viewModel.addSubject(subject)
                }
            }

        view.findViewById<Button>(R.id.button_subject_go_to_subject_list).apply {
            setOnClickListener {
                view.findNavController().navigate(R.id.action_subjectFragment_to_subjectListFragment)
            }
        }
}
}

