package com.example.mmvm_demo.data.repository

import com.example.mmvm_demo.data.model.Student
import com.example.mmvm_demo.data.remote.RetrofitInstance

class StudentRepository {
    private val apiService = RetrofitInstance.api

    suspend fun getStudents(): List<Student> {
        return try {
            apiService.getStudents().data
        } catch (e: Exception) {
            emptyList()
        }
    }
}