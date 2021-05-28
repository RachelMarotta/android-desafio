package com.rachelmarotta.desafioandroid.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rachelmarotta.desafioandroid.R
import com.rachelmarotta.desafioandroid.model.Item

class RepositoryViewHolder(listView: View) : RecyclerView.ViewHolder(listView) {

    private val repositoryPhoto: ImageView = listView.findViewById(R.id.repository_photo)
    private val repositoryName: TextView = listView.findViewById(R.id.repository_name)
    private val repositoryDescription: TextView = listView.findViewById(R.id.repository_description)
    private val repositoryAuthor: TextView = listView.findViewById(R.id.repository_author)
    private val repositoryStars: TextView = listView.findViewById(R.id.repository_stars)
    private val repositoryForks: TextView = listView.findViewById(R.id.repository_forks)

    fun bind(item: Item) {
        Glide.with(itemView.context).load(item.owner.avatar_url).into(repositoryPhoto)
        repositoryName.text = item.name
        repositoryDescription.text = item.description
        repositoryAuthor.text = item.owner.login
        repositoryStars.text = item.stargazers_count.toString()
        repositoryForks.text = item.forks_count.toString()
    }
}