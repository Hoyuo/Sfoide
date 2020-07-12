package com.studyfork.sfoide.ui.main

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.studyfork.sfoide.data.Person

@BindingAdapter("bind:refresh")
fun SwipeRefreshLayout.setRefresh(viewModel: MainViewModel) {
    setOnRefreshListener {
        viewModel.getPersonList()
    }

    isRefreshing = false
}

@BindingAdapter("bind:items")
fun RecyclerView.setItems(item: List<Person>?) {
    (adapter as? MainAdapter)?.submitList(item ?: emptyList())
}
