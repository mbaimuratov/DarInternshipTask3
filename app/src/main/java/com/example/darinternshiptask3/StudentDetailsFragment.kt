package com.example.darinternshiptask3

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.darinternshiptask3.databinding.StudentDetailsFragmentBinding

class StudentDetailsFragment : Fragment(R.layout.student_details_fragment) {
    private lateinit var binding: StudentDetailsFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val student = requireArguments().getParcelable<Student>("student_constant")
        binding = StudentDetailsFragmentBinding.bind(view)
        binding.apply {
            tvDetailName.text = student?.name
            tvDetailId.text = student?.id.toString()
            tvDetailSurname.text = student?.surname
            tvDetailGrade.text = student?.grade.toString()
        }
    }
}