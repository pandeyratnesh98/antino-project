package com.example.zomatosearch.network

import android.content.Context
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.payoassignment.home.jsonListener
import org.json.JSONException
import org.json.JSONObject


class myApi(private val ctx: Context, private val listener: jsonListener, val url: String, val query: String) {

     val requestQueue = Volley.newRequestQueue(ctx)
val userkey:String="dde8f10728f82e0829e1c581d5debe77"
     fun Jsonparse(){

          val getRequest: StringRequest = object : StringRequest(Method.GET, url+query, Response.Listener { response ->
               val jsonObject:JsonParser=JsonParser(response)
               listener?.getJsonData(jsonObject.jsonObject)
          },
                  Response.ErrorListener { volleyError ->
                  listener?.getJsonfetchingFailed(volleyError.message.toString())

                  }) {


               @Throws(AuthFailureError::class)
               override fun getHeaders(): Map<String, String> {
                    val headers: MutableMap<String, String> = HashMap()
                    headers["Accept"] = "application/json"
                    headers["user-key"] = userkey
                    return headers
               }
          }
          requestQueue.add(getRequest)
     }

}