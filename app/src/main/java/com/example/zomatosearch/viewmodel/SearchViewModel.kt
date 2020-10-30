package com.example.zomatosearch.viewmodel

import android.content.Context
import android.view.View
import androidx.lifecycle.ViewModel
import com.example.payoassignment.home.jsonListener
import com.example.zomatosearch.repos.searchRepo
import com.example.zomatosearch.ui.searchListener

class SearchViewModel(private val repo: searchRepo, private val ctx: Context):ViewModel() {
    var searchQuery:String?=null
var listener:searchListener?=null
var jsonListener:jsonListener?=null
    val url:String="https://developers.zomato.com/api/v2.1/search?q="
fun searchRestaurent(view:View){
    listener?.onStarted()
if (searchQuery.isNullOrEmpty()){
    listener?.OnFailed("Enter some text first to start searching")
    return
}
repo.searchHotel(searchQuery!!,jsonListener!!,url,ctx)
}

}
