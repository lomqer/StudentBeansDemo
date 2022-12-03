package com.dziaber.studentbeansdemo.ui.photos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dziaber.studentbeansdemo.data.PhotoRepository
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException


class PhotosViewModel(private val photoRepository: PhotoRepository) : ViewModel() {

    private val _photos = MutableLiveData<List<Photo>>()
    val photos: LiveData<List<Photo>> = _photos

    /**
     * Returns a Photo object with data provided in [jsonObject]
     */
    private fun photoFromJson(jsonObject: JSONObject): Photo{
        return Photo(title = jsonObject.get("title") as String,
            thumbnailUrl = jsonObject.get("thumbnailUrl") as String)
    }

    fun updatePhotos(){
        photoRepository.getPhotos().enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {}

            override fun onResponse(call: Call, response: Response){
                response.body()?.string()?.let {
                    val jsonArray = JSONArray(it)
                    val listOfPhoto = List(jsonArray.length()){
                            it2 -> photoFromJson(jsonArray.getJSONObject(it2)) }
                    _photos.postValue(listOfPhoto)
                }
            }
        })
    }
}