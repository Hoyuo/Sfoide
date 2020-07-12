package com.studyfork.sfoide.ui.main

import androidx.lifecycle.*
import com.studyfork.sfoide.data.Person
import com.studyfork.sfoide.domain.WebApi
import com.studyfork.sfoide.util.Event
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class MainViewModel : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    private val _personList: MutableLiveData<List<Person>> = MutableLiveData(listOf())
    val personList: LiveData<List<Person>>
        get() = _personList

    private val _event = MutableLiveData<Event<Pair<String, Person>>>()
    val event: LiveData<Event<Pair<String, Person>>>
        get() = _event


    fun getPersonList() {
        compositeDisposable.add(
            WebApi.getPersonList()
                .subscribeOn(Schedulers.io())
                .subscribeBy(
                    onError = {
                        Timber.e(it)
                    },
                    onNext = {
                        _personList.postValue(it.results)
                    }
                )
        )
    }

    fun onItemClicked(person: Person) {
        _event.postValue(Event(ITEM_CLICK to person))
    }

    companion object {
        val ITEM_CLICK = "ITEM_CLICK"
    }
}


