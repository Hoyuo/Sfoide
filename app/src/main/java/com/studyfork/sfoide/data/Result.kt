package com.studyfork.sfoide.data


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Result(
    @SerializedName("info")
    val info: Info,
    @SerializedName("results")
    val results: List<Person>
): Parcelable