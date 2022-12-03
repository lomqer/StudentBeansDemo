package com.dziaber.studentbeansdemo.data

import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.Request

/**
 * A class used to build the connection to the endpoint specified at [apiUrl]
 */
class PhotoRepository(private val apiUrl: String) {
    private val client = OkHttpClient()
    /**
     * Returns a *Call* which can be enqueued to retrieve data from the endpoint
     */
    fun getPhotos(): Call{
        val request = Request.Builder()
            .url(apiUrl)
            .build()
        return client.newCall(request)
    }
}