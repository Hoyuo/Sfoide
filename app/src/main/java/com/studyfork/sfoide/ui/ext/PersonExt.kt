package com.studyfork.sfoide.ui.ext

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.studyfork.sfoide.data.Person

@BindingAdapter("bind:image")
fun ImageView.setImage(person: Person?) {
    Glide.with(context)
        .load(person?.picture?.large)
        .apply(RequestOptions().circleCrop())
        .into(this)
}

@BindingAdapter("bind:name")
fun TextView.setName(person: Person?) {
    val nat = when (person?.nat) {
        "AU", "au" -> "\uD83C\uDDE6\uD83C\uDDFA"
        "BR", "br" -> "\uD83C\uDDE7\uD83C\uDDF7"
        "CA", "ca" -> "\uD83C\uDDE8\uD83C\uDDE6"
        "CH", "ch" -> "\uD83C\uDDE8\uD83C\uDDED"
        "DE", "de" -> "\uD83C\uDDE9\uD83C\uDDEA"
        "DK", "dk" -> "\uD83C\uDDE9\uD83C\uDDF0"
        "ES", "ed" -> "\uD83C\uDDEA\uD83C\uDDF8"
        "FI", "fi" -> "\uD83C\uDDEB\uD83C\uDDEE"
        "FR", "fr" -> "\uD83C\uDDEB\uD83C\uDDF7"
        "GB", "gb" -> "\uD83C\uDDEC\uD83C\uDDE7"
        "IE", "ie" -> "\uD83C\uDDEE\uD83C\uDDEA"
        "IR", "ir" -> "\uD83C\uDDEE\uD83C\uDDF7"
        "NO", "no" -> "\uD83C\uDDF3\uD83C\uDDF4"
        "NL", "nl" -> "\uD83C\uDDF3\uD83C\uDDF1"
        "NZ", "nz" -> "\uD83C\uDDF3\uD83C\uDDFF"
        "TR", "tr" -> "\uD83C\uDDF9\uD83C\uDDF7"
        "US", "us" -> "\uD83C\uDDFA\uD83C\uDDF8"
        else -> ""
    }

    val gender = when (person?.gender) {
        "male", "MALE" -> "\uD83D\uDE4B\u200D\u2642\uFE0F"
        "female", "FEMALE" -> "\uD83D\uDE4B\u200D\u2640\uFE0F"
        else -> ""
    }
    text = ("${person?.name?.first} ${person?.name?.last} (${person?.dob?.age}) $gender $nat")
}

@BindingAdapter("bind:email")
fun TextView.setEmail(person: Person?) {
    text = ("\uD83D\uDCE7" + person?.email)
}

@BindingAdapter("bind:telephonenumber")
fun TextView.setTelePhoneNumber(person: Person?) {
    text = ("\uD83D\uDCF1" + person?.phone)
}

@BindingAdapter("bind:phonenumber")
fun TextView.setPhoneNumber(person: Person?) {
    text = ("\u260E\uFE0F" + person?.cell)
}
