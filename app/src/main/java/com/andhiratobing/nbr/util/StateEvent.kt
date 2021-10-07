package com.andhiratobing.nbr.util

sealed class StateEvent {

    object Event : StateEvent()
    object None : StateEvent()
}
