package com.studyfork.sfoide.ui.main

import androidx.recyclerview.widget.DiffUtil
import com.studyfork.sfoide.data.Person

class PersonDiffCallBack : DiffUtil.ItemCallback<Person>() {
    override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean {
        return oldItem == newItem
    }

}