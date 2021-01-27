package com.example.darinternshiptask3

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.darinternshiptask3.databinding.StudentListFragmentBinding
import com.google.android.material.snackbar.Snackbar

class StudentListFragment : Fragment(R.layout.student_list_fragment), StudentListAdapter.OnStudentItemClicked {

    private lateinit var studentsStorage: StudentsStorage
    private lateinit var adapter: StudentListAdapter
    private lateinit var binding: StudentListFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = StudentListAdapter(this)
        binding = StudentListFragmentBinding.bind(view)
        studentsStorage = StudentsStorage.getInstance()!!

        binding.rvStudentList.adapter = adapter

        studentsStorage.createDummyStudents()
        addStudentToLayout()

        binding.addStudent.setOnClickListener {
            val name = binding.etName.text.toString()
            if (name == "") {
                val student = Student(StudentsStorage().getId())
                if (studentsStorage.hasStudent(student)) {
                    Snackbar.make(it, "Student is already present", Snackbar.LENGTH_SHORT).show()
                } else {
                    studentsStorage.addStudent(student)
                    studentsStorage.incrementIds()
                }
            } else {
                val student = Student(name = name, id = StudentsStorage().getId())
                if (studentsStorage.hasStudent(student)) {
                    Snackbar.make(it, "Student is already present", Snackbar.LENGTH_SHORT).show()
                } else {
                    studentsStorage.addStudent(student)
                    studentsStorage.incrementIds()
                }
            }
            addStudentToLayout()
        }
    }

    private fun addStudentToLayout() {
        adapter.submitList(studentsStorage.getStudents())
    }

    override fun onItemClicked(student: Student?) {
        val direction = R.id.action_studentListFragment_to_studentDetailsFragment
        val bundle = Bundle()
        bundle.putParcelable("student_constant", student)
        findNavController().navigate(direction, bundle)
    }

    override fun deleteStudent(student: Student) {
        studentsStorage.deleteStudent(student)
        addStudentToLayout()
    }

}