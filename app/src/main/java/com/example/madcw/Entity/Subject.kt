package com.example.madcw.Entity

data class Subject(
    val id: Long,
    val name: String,
    val code: String,
    val categoryName: String?,
    val categoryId: Long?,
    val degreeName: String?,
    val degreeId: Long?
)

