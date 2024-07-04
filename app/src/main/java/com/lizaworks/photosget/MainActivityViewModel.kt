package com.lizaworks.photosget

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lizaworks.photosget.network.Photo
import com.lizaworks.photosget.network.RetrofitClient
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(MainContentState())
    val uiState = _uiState.asStateFlow()

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _uiState.value =
            _uiState.value.copy(isLoading = false, errorMessage = "Something went wrong")


    }

    init {
        fetchData()
    }

    fun fetchData() {
        _uiState.value =
            _uiState.value.copy(isLoading = true)


        viewModelScope.launch(exceptionHandler) {
            val results = RetrofitClient.getInstance().getPhotos()
            _uiState.value = MainContentState(isLoading = false, photos = results)
        }
    }


    data class MainContentState(
        val isLoading: Boolean = true,
        val photos: List<Photo> = emptyList(),
        val errorMessage: String? = null
    )

}