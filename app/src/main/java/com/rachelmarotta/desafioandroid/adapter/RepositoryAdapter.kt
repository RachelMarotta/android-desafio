package com.rachelmarotta.desafioandroid.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rachelmarotta.desafioandroid.R
import com.rachelmarotta.desafioandroid.model.Item

class RepositoryAdapter(private val listRepositories: List<Item>): RecyclerView.Adapter<RepositoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val card = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.repository_card, parent, false)

        return RepositoryViewHolder(card)
    }

    override fun getItemCount(): Int {
        return listRepositories.size
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
    return holder.bind(listRepositories[position])
    }
}