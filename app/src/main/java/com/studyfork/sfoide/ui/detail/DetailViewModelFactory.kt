package com.studyfork.sfoide.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.studyfork.sfoide.data.Person

class DetailViewModelFactory(
    private val person: Person?
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailViewModel(person) as T
    }
}