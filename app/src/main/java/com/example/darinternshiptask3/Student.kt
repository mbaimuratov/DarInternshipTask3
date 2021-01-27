package com.example.darinternshiptask3

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Student(
    val id: Int,
    val name: String? = null,
    val surname: String? = null,
    val grade: Double? = null,
    val image: String? = null
    ) : Parcelable
