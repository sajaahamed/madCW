package com.example.madcw.ViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.madcw.Entity.DegreeSub
import com.example.madcw.Entity.Subject
import com.example.madcw.api.RetrofItInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DegreeViewModel : ViewModel() {

    private val _degrees = MutableStateFlow<List<DegreeSub>>(emptyList())
    val degrees: StateFlow<List<DegreeSub>> get() = _degrees

    // Fetch degrees with subjects
    fun fetchDegrees() {
        viewModelScope.launch {
            try {
                val degreeList = RetrofItInstance.degreeApi.getDegrees() // List<DegreeRequest>

                val degreeSubList = degreeList.map { degree ->
                    // Fetch subjects for each degree
                    val subjects: List<Subject> = try {
                        RetrofItInstance.degreeApi.getSubjectsByDegree(degree.id)
                    } catch (e: Exception) {
                        Log.e("DegreeVM", "Failed to fetch subjects for ${degree.name}: ${e.message}")
                        emptyList()
                    }

                    Log.d("DegreeVM", "Degree ${degree.name} has subjects: ${subjects.map { it.name }}")

                    DegreeSub(
                        id = degree.id,
                        name = degree.name,
                        subjects = subjects,
                        isExpanded = false // initially collapsed
                    )
                }

                _degrees.value = degreeSubList
            } catch (e: Exception) {
                Log.e("DegreeVM", "Failed to fetch degrees: ${e.message}")
            }
        }
    }

    // Toggle expand/collapse
    fun toggleDegreeExpanded(degreeId: Long) {
        _degrees.value = _degrees.value.map { degree ->
            if (degree.id == degreeId) {
                Log.d("DegreeVM", "Toggling ${degree.name}")
                degree.copy(isExpanded = !degree.isExpanded)
            } else degree
        }
    }
}
