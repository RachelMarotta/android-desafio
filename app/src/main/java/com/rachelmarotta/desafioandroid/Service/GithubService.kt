package com.rachelmarotta.desafioandroid.Service

import com.rachelmarotta.desafioandroid.model.GithubListRepository
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Call

interface GithubService {

    @GET("/search/repositories?q=language:kotlin&sort=stars")
    fun getListRepositories(@Query("page")page: Int) : Call<GithubListRepository>
}
