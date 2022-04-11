package com.rchyn.amphibians.ui.fragment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rchyn.amphibians.data.network.AmphibianApi
import com.rchyn.amphibians.model.Amphibian
import kotlinx.coroutines.launch

enum class AmphibianApiStatus {
    LOADING,
    ERROR,
    DONE
}

class AmphibianViewModel : ViewModel() {

    private val _status = MutableLiveData<AmphibianApiStatus>()
    val status: LiveData<AmphibianApiStatus> = _status

    private val _amphibiansList = MutableLiveData<List<Amphibian>>()
    val amphibiansList: LiveData<List<Amphibian>> = _amphibiansList

    private val _amphibian = MutableLiveData<Amphibian>()
    val amphibian: LiveData<Amphibian> = _amphibian

    init {
        getAmphibians()
    }

    private fun getAmphibians() {
        viewModelScope.launch {
            _status.value = AmphibianApiStatus.LOADING
            try {
                val data = AmphibianApi.retrofitService.getAmphibians()
                _amphibiansList.value = data
                _status.value = AmphibianApiStatus.DONE
            } catch (e: Exception) {
                _amphibiansList.value = listOf()
                _status.value = AmphibianApiStatus.ERROR
            }
        }
    }

    fun onAmphibianClicked(amphibian: Amphibian) {
        _amphibian.value = amphibian
    }
}