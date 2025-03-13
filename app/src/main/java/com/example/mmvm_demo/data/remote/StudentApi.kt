package com.example.mmvm_demo.data.remote

import com.example.mmvm_demo.data.model.Student
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface StudentApiService {
    @GET("users")
    suspend fun getStudents(@Query("page") page: Int = 1): StudentResponse
}

data class StudentResponse(val data: List<Student>)

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://reqres.in/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: StudentApiService by lazy {
        retrofit.create(StudentApiService::class.java)
    }
}
