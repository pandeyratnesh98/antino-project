package com.example.zomatosearch.ui

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.payoassignment.home.jsonListener

import com.example.zomatosearch.R
import com.example.zomatosearch.databinding.ActivitySearchBinding
import com.example.zomatosearch.network.data.hotel
import com.example.zomatosearch.ui.adapter.searchAdapter
import com.example.zomatosearch.util.showDialog
import com.example.zomatosearch.util.toast
import com.example.zomatosearch.viewmodel.SearchViewModel
import org.json.JSONArray
import org.json.JSONObject
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class searchActivity : AppCompatActivity(),KodeinAware,searchListener, jsonListener {
    val hotelList:ArrayList<hotel>?=ArrayList()
    private lateinit var linearLayoutManager: LinearLayoutManager
    var binding: ActivitySearchBinding?=null
    override val kodein: Kodein by kodein { this }
    private val factory:SearchViewModelFactory by instance()
    var pd:Dialog?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_search
        );
        val viewModel= ViewModelProvider(this, factory).get(SearchViewModel::class.java)
        binding?.viewmodel=viewModel
        viewModel.listener=this
        viewModel.jsonListener=this
pd=showDialog("Please wait while we loading...")
        hotelList?.clear()

    }

    override fun onStarted() {
pd?.show()
    }

    override fun OnFinished() {
        pd?.dismiss()
    }

    override fun OnFailed(message: String) {
        pd?.dismiss()
toast(message)
    }

    override fun onJsondatafetching() {
pd?.show()
    }

    override fun getJsonData(response: JSONObject) {
pd?.dismiss()
        var array: JSONArray =response.getJSONArray("restaurants");
        for (i in 0..array.length()-1){
            var data:JSONObject=array.getJSONObject(i)

                hotelList?.add(hotel(data.getJSONObject("restaurant").getString("name"),
                        data.getJSONObject("restaurant").getJSONObject("user_rating").getString("aggregate_rating"),
                        data.getJSONObject("restaurant").getString("thumb"),
                        data.getJSONObject("restaurant").getJSONObject("location").getString("locality")))


                   }

        linearLayoutManager = LinearLayoutManager(applicationContext)
        binding?.hotels?.layoutManager = linearLayoutManager
        val adaper:searchAdapter= searchAdapter(hotelList!!, applicationContext)
        binding?.hotels?.adapter=adaper
        adaper.notifyDataSetChanged()
    }

    override fun getJsonfetchingFailed(message: String) {
        pd?.dismiss()
       toast("json data failed to load: "+message)
    }

}