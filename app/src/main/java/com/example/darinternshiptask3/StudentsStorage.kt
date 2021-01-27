package com.example.darinternshiptask3

import java.util.*

class StudentsStorage {
    private var idStudent = 1000
    private val tempTrash = LinkedList<Student>()
    private val studentsSet = mutableSetOf<Student>()

    companion object {
        private var instance: StudentsStorage? = null
        fun getInstance(): StudentsStorage? {
            if (instance == null) {
                synchronized(this) {
                    instance = StudentsStorage()
                }
            }
            return instance
        }
    }

    fun getId() = idStudent

    fun hasStudent(student: Student) = studentsSet.contains(student)
    
    fun addStudent(student: Student) {
        studentsSet.add(student)
    }

    fun incrementIds() {
        idStudent++
    }

    fun getStudents() = studentsSet.toList()

    fun createDummyStudents() {
        for (i in 1..10) {
            studentsSet.add(Student(name = "Meiirbek$i", id = i + 100))
        }
    }

    fun deleteStudent(student: Student) {
        studentsSet.remove(student)

        if (tempTrash.size < 5) {
            tempTrash.add(student)
        } else {
            tempTrash.removeLast()
            tempTrash.add(student)
        }
    }

}