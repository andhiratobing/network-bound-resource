package com.andhiratobing.nbr.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.andhiratobing.nbr.adapter.UserAdapter
import com.andhiratobing.nbr.databinding.ActivityMainBinding
import com.andhiratobing.nbr.util.DataState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initAdapter()
        initObserver()
        getDataUserRandom()
    }

    private fun getDataUserRandom() {
        lifecycleScope.launch {
            mainViewModel.setUserRandom()
        }
    }

    private fun initObserver() {
        binding.apply {
            mainViewModel.getUserLiveData.observe(this@MainActivity, {
                it.data?.let { listUser -> userAdapter.submitList(listUser) }
                progressBar.isVisible = it is DataState.Loading && it.data.isNullOrEmpty()
                tvMessageError.isVisible = it is DataState.Error && it.data.isNullOrEmpty()
                tvMessageError.text = it.errorBody?.message()
            })
        }
    }

    private fun initAdapter() {
        binding.apply {
            rvUser.layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            userAdapter = UserAdapter()
            rvUser.adapter = userAdapter
        }
    }
}