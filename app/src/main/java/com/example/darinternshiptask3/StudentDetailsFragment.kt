package com.example.darinternshiptask3

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.view.View
import androidx.fragment.app.Fragment
import com.example.darinternshiptask3.databinding.StudentDetailsFragmentBinding


class StudentDetailsFragment : Fragment(R.layout.student_details_fragment) {
    private lateinit var binding: StudentDetailsFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val student = requireArguments().getParcelable<Student>(STUDENT_KEY)

        binding = StudentDetailsFragmentBinding.bind(view)

        binding.apply {
            tvDetailName.text = student?.name
            tvDetailId.text = student?.id.toString()
            tvDetailSurname.text = student?.surname
            tvDetailGrade.text = student?.grade.toString()

            if (student?.image != null) {
                val decodedString: ByteArray = Base64.decode(student.image, Base64.DEFAULT)
                ivDetailImg.setImageBitmap(
                    BitmapFactory.decodeByteArray(
                        decodedString,
                        0,
                        decodedString.size
                    )
                )
            }
        }
    }
}