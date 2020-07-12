package com.studyfork.sfoide.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.studyfork.sfoide.R
import com.studyfork.sfoide.data.Person
import com.studyfork.sfoide.databinding.ViewholderItemBinding

class MainAdapter(
    val viewModel: MainViewModel
) : ListAdapter<Person, MainAdapter.ItemViewHolder>(PersonDiffCallBack()) {

    class ItemViewHolder(
        private val binding: ViewholderItemBinding,
        private val viewModel: MainViewModel
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(person: Person) {
            binding.vm = person
            binding.setCardClick { viewModel.onItemClicked(person) }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.viewholder_item,
                parent,
                false
            ),
            viewModel
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
