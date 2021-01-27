package com.example.darinternshiptask3

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.darinternshiptask3.databinding.StudentListItemBinding

class StudentListAdapter(private val onItemClicked: OnStudentItemClicked) : RecyclerView.Adapter<StudentListAdapter.StudentViewHolder>() {

    private var list = listOf<Student>()

    fun submitList(newList: List<Student>?) {
        list = newList ?: listOf()
        notifyDataSetChanged()
    }

    inner class StudentViewHolder(private val binding: StudentListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Student) {
            binding.tvName.text = item.name ?: "Nameless soldier"
            binding.tvStudentId.text = item.id.toString()

            binding.root.setOnClickListener {
                onItemClicked.onItemClicked(
                    item
                )
            }

            binding.actionDeleteStudent.setOnClickListener {
                onItemClicked.deleteStudent(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val binding = StudentListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StudentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    interface OnStudentItemClicked {
        fun onItemClicked(student: Student?)
        fun deleteStudent(student: Student)
    }
}