package com.example.darinternshiptask3

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Student(
    val id: Int,
    val name: String? = "Name",
    val surname: String? = "Surname",
    val grade: Double? = 0.0,
    val image: String? = null
    ) : Parcelable
