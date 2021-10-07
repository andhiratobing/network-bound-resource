package com.andhiratobing.nbr.view

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.andhiratobing.nbr.adapter.UserAdapter
import com.andhiratobing.nbr.data.model.User
import com.andhiratobing.nbr.databinding.ActivityMainBinding
import com.andhiratobing.nbr.util.DataState
import com.andhiratobing.nbr.util.StateEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        initObserver()
        mainViewModel.setStateEvent(StateEvent.Event)
    }

    private fun initObserver() {
        mainViewModel.dataState.observe(this, {
            when (it) {
                is DataState.Loading -> {
                    showProgressBar(true)
                }
                is DataState.Success<List<User>> -> {
                    showProgressBar(false)
                    getDataUser(it.data)
                }
                is DataState.Error -> {

                    showError(it.exception.message)
                }
            }
        })
    }

    private fun getDataUser(user: List<User>) {
        binding.apply {
            rvUser.layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            userAdapter = UserAdapter()
            rvUser.adapter = userAdapter
            userAdapter.submitList(user as ArrayList)
        }

    }

    private fun showProgressBar(state: Boolean) {
        binding.apply {
            progressBar.visibility = if (state) View.VISIBLE else View.GONE
        }
    }

    private fun showError(message: String?) {
        binding.apply {
            tvMessageError.text = message
        }
    }
}






























