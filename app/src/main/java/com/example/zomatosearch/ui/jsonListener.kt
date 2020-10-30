package com.example.payoassignment.home

import org.json.JSONObject

interface jsonListener {
    fun onJsondatafetching()
    fun getJsonData(response:JSONObject)
    fun getJsonfetchingFailed(message:String)
}