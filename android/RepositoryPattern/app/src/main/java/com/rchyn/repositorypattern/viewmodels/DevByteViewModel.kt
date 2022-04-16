package com.rchyn.repositorypattern.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.rchyn.repositorypattern.database.VideosDatabase
import com.rchyn.repositorypattern.database.getDatabase
import com.rchyn.repositorypattern.domain.DevByteVideo
import com.rchyn.repositorypattern.network.DevByteNetwork
import com.rchyn.repositorypattern.network.asDomainModel
import com.rchyn.repositorypattern.repository.VideoRepository
import kotlinx.coroutines.launch
import okio.IOException

class DevByteViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val videoRepository = VideoRepository(getDatabase(application))

    val playlist = videoRepository.videos

    private val _eventNetworkError = MutableLiveData(false)
    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError


    private val _isNetworkErrorShown = MutableLiveData(false)
    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown


    init {
        refreshDataFromNetwork()
    }

    private fun refreshDataFromNetwork() = viewModelScope.launch {
        try {
            videoRepository.refreshVideos()
            _eventNetworkError.value = false
            _isNetworkErrorShown.value = false
        } catch (networkError: IOException) {
            _eventNetworkError.value = true
        }
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }

    class Factory(private val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(DevByteViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return DevByteViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}