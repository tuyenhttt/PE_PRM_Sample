package com.example.mmvm_demo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mmvm_demo.data.model.Student
import com.example.mmvm_demo.data.repository.StudentRepository
import kotlinx.coroutines.launch

class StudentViewModel : ViewModel() {
    private val repository = StudentRepository()

    private val _students = MutableLiveData<List<Student>>(emptyList())
    val students: LiveData<List<Student>> = _students

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        fetchStudents()
    }

    fun fetchStudents() {
        viewModelScope.launch {
            _isLoading.value = true
            _students.value = repository.getStudents()
            _isLoading.value = false
        }
    }

    fun addStudent(student: Student) {
        val currentList = _students.value ?: emptyList()
        _students.value = currentList + student
    }

    fun removeStudent(student: Student) {
        _students.value = _students.value?.filter { it.id != student.id }
    }
}