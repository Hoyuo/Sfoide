package com.studyfork.sfoide.domain

import com.studyfork.sfoide.data.Result
import io.reactivex.Flowable
import retrofit2.http.GET

interface WebApiInterface {

    @GET("api/?results=20")
    fun getPersonList(): Flowable<Result>
}