package com.jun.slice.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.jun.slice.model.Repository.MainRepository
import com.jun.slice.utils.Resource
import kotlinx.coroutines.Dispatchers

class MainViewModel(private val mainRepository: MainRepository):ViewModel() {


    fun getTweets() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data= mainRepository.getTweets().data))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}