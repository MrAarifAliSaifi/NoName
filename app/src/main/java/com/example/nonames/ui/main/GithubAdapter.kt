package com.example.nonames.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nonames.data.model.GithubData
import com.example.nonames.databinding.GithubItemBinding

class GithubAdapter(private val list: MutableList<GithubData>) :
    RecyclerView.Adapter<GithubAdapter.GithubViewHolder>() {

    inner class GithubViewHolder(private val binding: GithubItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(repo: GithubData) {
            binding.apply {
                tvName.text = repo.name
                tvDesc.text = repo.description ?: "No description"
                tvLang.text = repo.language ?: "Unknown"
                tvStars.text = "⭐ ${repo.stargazers_count}"
                tvForks.text = "🍴 ${repo.forks_count}"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubViewHolder {
        val binding = GithubItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GithubViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GithubViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    fun addRepos(newRepos: List<GithubData>) {
        val oldSize = list.size
        list.addAll(newRepos)
        notifyItemRangeInserted(oldSize, newRepos.size)
    }
}

