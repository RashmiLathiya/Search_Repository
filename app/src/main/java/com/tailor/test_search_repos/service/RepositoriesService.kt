package com.tailor.test_search_repos.service

import com.example.example.Owner
import com.tailor.test_search_repos.model.Datum
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*


interface RepositoriesService {

    @GET("search/repositories?")
    fun searchRepos(@Query("q") searchTerm: String): Call<Datum>


}