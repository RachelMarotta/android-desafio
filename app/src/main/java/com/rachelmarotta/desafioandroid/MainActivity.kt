package com.rachelmarotta.desafioandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rachelmarotta.desafioandroid.service.ApiService
import com.rachelmarotta.desafioandroid.service.GithubService
import com.rachelmarotta.desafioandroid.adapter.RepositoryAdapter
import com.rachelmarotta.desafioandroid.model.GithubListRepository
import com.rachelmarotta.desafioandroid.model.Item
import retrofit2.Callback
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var recyclerview: RecyclerView
    lateinit var layoutManager: LinearLayoutManager
    lateinit var repositoryAdapter: RepositoryAdapter
    lateinit var listRepositories: MutableList<Item>
    var page: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerview = findViewById(R.id.recyclerview_list)
        layoutManager = LinearLayoutManager(this)
        recyclerview.layoutManager = layoutManager
        listRepositories = arrayListOf()

        getRepositories(page)
    }

    fun getRepositories(page: Int) {

        val request = ApiService.buildService(GithubService::class.java)
        val call = request.getListRepositories(page)

        call.enqueue(object : Callback<GithubListRepository> {

            override fun onResponse(
                call: Call<GithubListRepository>,
                response: Response<GithubListRepository>
            ) {
                if(response.isSuccessful) {
                    listRepositories.addAll(response.body()!!.items)
                    repositoryAdapter = RepositoryAdapter(listRepositories)

                    if(page > 1) {
                        val lastVisible = layoutManager.findLastCompletelyVisibleItemPosition()
                        repositoryAdapter.notifyItemInserted(lastVisible)
                    } else {
                        recyclerview.apply {
                            setHasFixedSize(true)
                            adapter = repositoryAdapter
                        }
                    }
                }
            }

            override fun onFailure(
                call: Call<GithubListRepository>,
                t: Throwable?
            ) {
//                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                t?.message?.let { Log.e("onFailure error", it) }
            }
        })

    }
}


