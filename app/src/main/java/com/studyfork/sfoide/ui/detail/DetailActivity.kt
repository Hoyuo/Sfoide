package com.studyfork.sfoide.ui.detail

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.studyfork.sfoide.R
import com.studyfork.sfoide.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
//
//    private val person: Person? by lazy {
//        intent.getParcelableExtra(DATA_PERSON) as? Person
//    }
//
//    private val viewModel: DetailViewModel by lazy {
//        ViewModelProvider(this, DetailViewModelFactory(person)).get(DetailViewModel::class.java)
//    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)

//        binding.vm = viewModel
//        binding.lifecycleOwner = this
//
//        Timber.d("$person")
    }

    companion object {
        val DATA_PERSON = "DATA_PERSON"
    }
}