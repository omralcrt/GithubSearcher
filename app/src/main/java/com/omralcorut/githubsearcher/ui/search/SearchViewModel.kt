package com.omralcorut.githubsearcher.ui.search

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.omralcorut.githubsearcher.models.Repository
import com.omralcorut.githubsearcher.models.User
import com.omralcorut.githubsearcher.repository.SearchRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class SearchViewModel @ViewModelInject constructor(
    private val searchRepository: SearchRepository
) : ViewModel() {

    private val _users = MutableLiveData<List<User>>(listOf())
    private val _repositories = MutableLiveData<List<Repository>>(listOf())
    private val _isResultEmpty = MutableLiveData<Boolean>(true)

    val users: LiveData<List<User>>
        get() = _users

    val repositories: LiveData<List<Repository>>
        get() = _repositories

    val isResultEmpty: LiveData<Boolean>
        get() = _isResultEmpty

    fun getUsers(query: String) {
        viewModelScope.launch {
            searchRepository.getUsers(query).map { it.items }
                .catch { exception ->
                    _users.value = listOf()
                    checkResultState()
                }
                .collect { items ->
                    _users.value = items
                    checkResultState()
                }
        }
    }

    fun getRepositories(query: String) {
        viewModelScope.launch {
            searchRepository.getRepositories(query).map { it.items }
                .catch { exception ->
                    _repositories.value = listOf()
                    checkResultState()
                }
                .collect { items ->
                    _repositories.value = items
                    checkResultState()
                }
        }
    }

    fun clearResults() {
        _users.value = listOf()
        _repositories.value = listOf()
        checkResultState()
    }

    private fun checkResultState() {
        _isResultEmpty.value = users.value!!.isEmpty() && repositories.value!!.isEmpty()
    }
}