package com.example.madcw.api

import com.example.madcw.Entity.DegreeRequest
import com.example.madcw.Entity.Subject
import retrofit2.http.GET
import retrofit2.http.Path

interface DegreeApi {
    @GET("api/degrees/all")
    suspend fun getDegrees(): List<DegreeRequest>

    @GET("api/degrees/{degreeId}/subjects")
    suspend fun getSubjectsByDegree(@Path("degreeId") degreeId: Long): List<Subject>
}