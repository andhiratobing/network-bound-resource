package com.andhiratobing.nbr.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andhiratobing.nbr.data.model.User
import com.andhiratobing.nbr.data.repositories.MainRepository
import com.andhiratobing.nbr.util.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {

    private var _getUserMutableLiveData: MutableLiveData<DataState<List<User>>> = MutableLiveData()
    val getUserLiveData: LiveData<DataState<List<User>>> get() = _getUserMutableLiveData

    fun setUserRandom() {
        viewModelScope.launch {
            mainRepository.getUser().collect {
                _getUserMutableLiveData.value = it
            }
        }
    }

}