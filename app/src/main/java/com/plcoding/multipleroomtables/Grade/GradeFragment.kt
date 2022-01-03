package com.plcoding.multipleroomtables.Grade

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
import androidx.navigation.fragment.navArgs
import com.plcoding.multipleroomtables.entities.Grade

class GradeFragment: Fragment() {


    private lateinit var viewModel: AddGradeViewModel
    val args: GradeFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
      viewModel=ViewModelProvider(requireActivity()).get(AddGradeViewModel::class.java)


        return inflater.inflate(R.layout.fragment_grade,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val grade=view.findViewById<EditText>(R.id.grade_value)
        val scaleEquation=view.findViewById<EditText>(R.id.grade_equation)
        val weight=view.findViewById<EditText>(R.id.grade_weight)

        view.findViewById<Button>(R.id.button_add_grade).apply {
            setOnClickListener {
                val gradeValue = grade.getText().toString().toFloat()
                val gradeScale = scaleEquation.text.toString().toFloat()
                val gradeWeight = weight.text.toString().toFloat()

                val grade = Grade(
                    grade = gradeValue,
                    scaleEquation = gradeScale,
                    weight = gradeWeight,
                    subjectName = args.subjectName,
                    studentIndex = args.studentIndex)
                    viewModel.addGrade(grade)
                }
            }


        view.findViewById<Button>(R.id.button_grade_go_to_grade_list).apply {
            setOnClickListener {
                val action = GradeFragmentDirections.actionGradeFragmentToGradeListFragment(args.studentIndex,args.subjectName)
                it.findNavController().navigate(action)            }
        }
        }
}

