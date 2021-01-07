package com.omralcorut.githubsearcher.ui.search

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.omralcorut.githubsearcher.models.Repository
import com.omralcorut.githubsearcher.models.User
import com.omralcorut.githubsearcher.repository.SearchRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class SearchViewModel @ViewModelInject constructor(
    private val searchRepository: SearchRepository
) : ViewModel() {

    private val _users = MutableLiveData<List<User>>(listOf())
    private val _repositories = MutableLiveData<List<Repository>>(listOf())

    val users: LiveData<List<User>>
        get() = _users

    val repositories: LiveData<List<Repository>>
        get() = _repositories


    init {
        getUsers()
        getRepositories()
    }

    fun getUsers() {
        viewModelScope.launch {
            searchRepository.getUsers("username").map { it.items }.collect { items ->
                _users.value = items
            }
        }
    }

    fun getRepositories() {
        viewModelScope.launch {
            searchRepository.getRepositories("reponame").map { it.items }.collect { items ->
                _repositories.value = items
            }
        }
    }
}