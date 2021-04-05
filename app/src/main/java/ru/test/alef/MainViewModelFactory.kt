package ru.test.alef

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.test.alef.repository.Repository

class MainViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}