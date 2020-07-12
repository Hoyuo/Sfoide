package com.studyfork.sfoide.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.studyfork.sfoide.data.Person
import com.studyfork.sfoide.util.Event

class DetailViewModel(
    val person: Person?
) : ViewModel() {
    private val _event = MutableLiveData<Event<Pair<String, String>>>()
    val event: LiveData<Event<Pair<String, String>>>
        get() = _event


    fun onItemClickEmail(email: String) {
        _event.postValue(Event(ITEM_CLICK_EMAIL to email))
    }

    fun onItemClickNumber(phoneNumber: String) {
        _event.postValue(Event(ITEM_CLICK_NUMBER to phoneNumber))
    }

    companion object {
        val ITEM_CLICK_EMAIL = "ITEM_CLICK_EMAIL"
        val ITEM_CLICK_NUMBER = "ITEM_CLICK_NUMBER"
    }
}