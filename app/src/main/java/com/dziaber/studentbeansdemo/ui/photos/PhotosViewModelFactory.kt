package com.dziaber.studentbeansdemo.ui.photos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dziaber.studentbeansdemo.data.PhotoRepository

class PhotosViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PhotosViewModel::class.java)) {
            return PhotosViewModel(
                    photoRepository = PhotoRepository(
                        apiUrl = "https://jsonplaceholder.typicode.com/photos"
                    )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}