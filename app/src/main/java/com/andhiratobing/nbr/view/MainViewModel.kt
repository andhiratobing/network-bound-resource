package com.andhiratobing.nbr.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andhiratobing.nbr.data.model.User
import com.andhiratobing.nbr.data.repositories.MainRepository
import com.andhiratobing.nbr.util.DataState
import com.andhiratobing.nbr.util.StateEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {

    private var _dataState: MutableLiveData<DataState<List<User>>> = MutableLiveData()
    val dataState: LiveData<DataState<List<User>>> get() = _dataState

    fun setStateEvent(stateEvent: StateEvent) {
        viewModelScope.launch {
            when (stateEvent) {
                is StateEvent.Event -> {
                    mainRepository.getUser().onEach {
                        _dataState.value = it
                    }.launchIn(viewModelScope)
                }

                is StateEvent.None -> {

                }
            }
        }
    }


}