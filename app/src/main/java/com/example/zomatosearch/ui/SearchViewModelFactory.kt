package com.example.zomatosearch.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.zomatosearch.repos.searchRepo
import com.example.zomatosearch.viewmodel.SearchViewModel

@Suppress("UNCHECKED_CAST")
class SearchViewModelFactory (private val repo: searchRepo,private val ctx: Context): ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SearchViewModel(repo,ctx) as T
    }
}