package com.example.zomatosearch.repos

import android.content.Context
import com.example.payoassignment.home.jsonListener
import com.example.zomatosearch.network.myApi
import com.example.zomatosearch.ui.searchListener

class searchRepo {

    fun searchHotel(query:String, listener: jsonListener, url:String, ctx: Context){
        val api = myApi(ctx,listener,url,query)
        api.Jsonparse()
    }
}