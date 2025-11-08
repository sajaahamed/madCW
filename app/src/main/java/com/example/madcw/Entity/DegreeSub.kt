package com.example.madcw.Entity

data class DegreeSub(
    val id: Long,
    val name: String,
    val subjects: List<Subject> = emptyList(),
    var isExpanded: Boolean = false
)
