package com.plcoding.multipleroomtables.Subject

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
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

        view.findViewById<Button>(R.id.button_add_subject).apply {
            setOnClickListener {
                    val subject= Subject("Meth","friday","187")
                    viewModel.addSubject(subject)
                }
            }
        Log.d("cret", "created")


        view.findViewById<Button>(R.id.button_subject_go_to_subject_list).apply {
            Log.d("click", "clikced")
            setOnClickListener {
                //view.findNavController().navigate(R.id.action_subjectFragment_to_subjectListFragment)
            }
        }
}
}

