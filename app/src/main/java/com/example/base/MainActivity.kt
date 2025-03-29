package com.example.base

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.base.base.BaseActivity
import com.example.base.database.model.MySample
import com.example.base.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override
    fun getLayoutId() = R.layout.activity_main

    private val viewModel: MainViewModel by viewModels()

    override fun setupUI() {

    }

    override fun setupData() {
        lifecycleScope.launch {
            viewModel.stateFlow.collect {
                binding.tvTitle.text = it.firstOrNull()?.name.toString()
            }
        }
        viewModel.getAll()
    }

    override fun setupListener() {
        binding.apply {
            btnInsert.setOnClickListener {
                viewModel.insert(MySample(null,"huanhundd", 24))
            }
        }
    }


}