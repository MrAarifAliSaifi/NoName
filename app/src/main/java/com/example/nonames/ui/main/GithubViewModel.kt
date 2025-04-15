package com.example.nonames.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nonames.data.model.GithubData
import com.example.nonames.data.repository.GithubRepo
import com.example.nonames.utils.Resources
import kotlinx.coroutines.launch
import javax.inject.Inject


class GithubViewModel @Inject constructor(
    private val repository: GithubRepo
) : ViewModel() {

    private val _repos = MutableLiveData<Resources<List<GithubData>>>()
    val repos: LiveData<Resources<List<GithubData>>> = _repos

    fun fetchRepos(user: String, page: Int) {
        viewModelScope.launch {
            _repos.postValue(Resources.Loading())
            _repos.postValue(repository.getRepos(user, page))
        }
    }
}

