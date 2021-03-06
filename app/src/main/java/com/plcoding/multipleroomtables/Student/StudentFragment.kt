package com.plcoding.multipleroomtables.Student

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
import com.plcoding.multipleroomtables.entities.Student
import com.plcoding.multipleroomtables.entities.Subject
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener

class StudentFragment: Fragment() {


    private lateinit var viewModel: AddStudentViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
      //val factory=AddUserViewModelFactory(requireActivity().application)
      viewModel=ViewModelProvider(requireActivity()).get(AddStudentViewModel::class.java)


        return inflater.inflate(R.layout.fragment_student,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val index = view.findViewById<EditText>(R.id.student_index)
        val name = view.findViewById<EditText>(R.id.student_name)
        view.findViewById<Button>(R.id.button_add_student).apply {
            setOnClickListener {
                val studentIndex: Int = index.getText().toString().toInt()
                val studentName = name.text.toString()
                    val student= Student(studentIndex,studentName)
                    viewModel.addStudent(student)
                }
            }


        view.findViewById<Button>(R.id.button_student_go_to_subject_list).apply {
            setOnClickListener {
                view.findNavController().navigate(R.id.action_studentFragment_to_subjectListFragment)
            }
        }
        }
}

