package com.studyfork.sfoide.ui.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.studyfork.sfoide.R
import com.studyfork.sfoide.databinding.ActivityMainBinding
import com.studyfork.sfoide.ui.detail.DetailActivity
import com.studyfork.sfoide.ui.main.MainViewModel.Companion.ITEM_CLICK
import com.studyfork.sfoide.util.EventObserver

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.lifecycleOwner = this
        binding.run {
            binding.vm = viewModel
            mainView.adapter = MainAdapter(viewModel)
        }

        viewModel.getPersonList()

        observeEvent()
    }

    private fun observeEvent() {
        viewModel.event.observe(this, EventObserver { (event, person) ->
            when (event) {
                ITEM_CLICK -> {
                    startActivity(Intent(this@MainActivity, DetailActivity::class.java).apply {
                        putExtra(DetailActivity.DATA_PERSON, person)
                    })
                }
            }
        })
    }

    override fun onBackPressed() {
        val temp = System.currentTimeMillis()
        val intervalTime = temp - backPressedTime

        if (intervalTime in 0..FINISH_INTERVAL_TIME) {
            super.onBackPressed()
        } else {
            Toast.makeText(this, "앱을 종료하시려면 한번 더 눌러주세요.", Toast.LENGTH_SHORT).show()
            backPressedTime = intervalTime
        }
    }

    companion object {
        private val FINISH_INTERVAL_TIME = 2000
        private var backPressedTime = 0L
    }
}