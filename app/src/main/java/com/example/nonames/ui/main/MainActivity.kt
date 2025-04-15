package com.example.nonames.ui.main

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nonames.databinding.MainActivityBinding
import com.example.nonames.utils.Resources
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: GithubViewModelFactory

    private val viewModel: GithubViewModel by viewModels { viewModelFactory }
    private lateinit var binding: MainActivityBinding
    private lateinit var adapter: GithubAdapter

    private var page = 1
    private var isLoading = false
    private val user = "google"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Dagger injection
        val appComponent = DaggerAppComponent.factory().create(applicationContext)
        appComponent.inject(this)

        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()
        observeViewmodel()

        viewModel.fetchRepos(user, page)
    }

    private fun setupRecyclerView() {
        adapter = GithubAdapter(mutableListOf())
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (!recyclerView.canScrollVertically(1) && !isLoading) {
                    page++
                    viewModel.fetchRepos(user, page)
                }
            }
        })
    }

    private fun observeViewmodel() {
        viewModel.repos.observe(this) {
            when (it) {
                is Resources.Loading -> {
                    isLoading = true
                    binding.progressBar.visibility = View.VISIBLE
                }

                is Resources.Success -> {
                    isLoading = false
                    binding.progressBar.visibility = View.GONE
                    val repoList = it.data ?: emptyList()
                    adapter.addRepos(repoList)
                }

                is Resources.Error -> {
                    isLoading = false
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                    Log.e("REPO", "Error: ${it.message}")
                }
            }
        }
    }
}
