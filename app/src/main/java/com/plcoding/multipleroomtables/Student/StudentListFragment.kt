package com.plcoding.multipleroomtables.Student

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.plcoding.multipleroomtables.R
import com.plcoding.multipleroomtables.entities.Student
import com.plcoding.multipleroomtables.entities.Subject
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class StudentListFragment: Fragment() {

    lateinit var viewModel: StudentListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var amt = arguments?.getString("amount")
        Log.d("amt",amt.toString())
        return inflater.inflate(R.layout.fragment_student_list,container,false)
    }
    override  fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory= StudentListViewModelFactory((requireNotNull(this.activity).application))

        viewModel=ViewModelProvider(requireActivity(),factory).
        get(StudentListViewModel::class.java)

        val studentListAdapter= StudentListAdapter(viewModel.students,viewModel)

//        viewModel.students.observe(viewLifecycleOwner,
//            Observer<List<Student>> { studentListAdapter.notifyDataSetChanged() }
//        )

        val layoutManager=LinearLayoutManager(view.context)
        view.findViewById<RecyclerView>(R.id.student_recyclerView).let {

            it.adapter=studentListAdapter
            it.layoutManager=layoutManager
        }
        view.findViewById<Button>(R.id.button_back_to_form).apply {
            setOnClickListener {
                it.findNavController().navigate(R.id.action_studentListFragment_to_studentFragment)
            }
        }
    }
}