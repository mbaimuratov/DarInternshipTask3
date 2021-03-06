package com.example.darinternshiptask3

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.darinternshiptask3.databinding.StudentListFragmentBinding
import com.google.android.material.snackbar.Snackbar

val STUDENT_KEY = "student_constant"

class StudentListFragment : Fragment(R.layout.student_list_fragment), StudentListAdapter.OnStudentItemClicked {


    private lateinit var studentsStorage: StudentsStorage
    private lateinit var adapter: StudentListAdapter
    private lateinit var binding: StudentListFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = StudentListAdapter(this)
        studentsStorage = StudentsStorage.getInstance()!!
        studentsStorage.createDummyStudents()
        addStudentToLayout()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        binding = StudentListFragmentBinding.bind(view)

        binding.rvStudentList.adapter = adapter

        binding.addStudent.setOnClickListener {
            val name = binding.etName.text.toString()
            if (name == "") {
                val student = Student(studentsStorage.getId())
                studentsStorage.addStudent(student)
                studentsStorage.updateId()
            } else {
                val student = Student(name = name, id = StudentsStorage().getId())
                if (studentsStorage.hasStudent(student)) {
                    Snackbar.make(it, "Student is already present", Snackbar.LENGTH_SHORT).show()
                } else {
                    studentsStorage.addStudent(student)
                    studentsStorage.updateId()
                }
            }
            refreshLayout()
            addStudentToLayout()
        }

        binding.restoreStudent.setOnClickListener {
            studentsStorage.restoreLastDeleted()
            addStudentToLayout()
        }
    }

    private fun refreshLayout() {
        binding.etName.text.clear()
        val imm: InputMethodManager =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(requireView().windowToken, 0)
    }

    private fun addStudentToLayout() {
        adapter.submitList(studentsStorage.getStudents())
    }

    override fun onItemClicked(student: Student?) {
        val direction = R.id.action_studentListFragment_to_studentDetailsFragment
        val bundle = Bundle()
        bundle.putParcelable(STUDENT_KEY, student)
        findNavController().navigate(direction, bundle)
    }

    override fun deleteStudent(student: Student) {
        studentsStorage.deleteStudent(student)
        addStudentToLayout()
    }
}