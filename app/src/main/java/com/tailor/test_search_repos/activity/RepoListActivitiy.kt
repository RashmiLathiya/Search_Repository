package com.tailor.test_search_repos.activity

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.example.Items
import com.tailor.test_search_repos.R
import com.tailor.test_search_repos.helper.RepositoriesAdapter
import com.tailor.test_search_repos.model.Datum
import com.tailor.test_search_repos.service.RepositoriesService
import com.tailor.test_search_repos.service.ServiceBuilder
import kotlinx.android.synthetic.main.activity_repolist.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RepoListActivitiy : AppCompatActivity() , RepositoriesAdapter.OnItemClickListener{

    private var itemList : List<Items> = ArrayList()
    private var recyclerView : RecyclerView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repolist)

       // val repositoryName = intent.getStringExtra("repositoryName")
        recyclerView = findViewById(R.id.recyclerView)
        inIt()

    }

    private fun inIt() {
        search_view.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            }
            override fun afterTextChanged(s: Editable) {
                    ic_close.setVisibility(View.VISIBLE)
                    rlList.setVisibility(View.VISIBLE)
                    search_show.setVisibility(View.GONE)
                loadRepositoryData(s.toString())
            }
        })

        ic_close.setOnClickListener {
            search_view.text.clear()
            rlList.setVisibility(View.GONE)

        }

        ic_back.setOnClickListener {
            onBackPressed()
        }
    }

    private fun loadRepositoryData(editable: String?) {
        val msg: String = editable.toString()
        if (msg.trim().isNotEmpty()) {
            val repositoriesService = ServiceBuilder.buildService(RepositoriesService::class.java)
            val requestCall = repositoriesService.searchRepos(editable.toString())

            requestCall.enqueue(object : Callback<Datum> {
                override fun onResponse(call: Call<Datum>, response: Response<Datum>) {

                    val searchResult = response?.body()
                    itemList = searchResult?.items!!
                    if (searchResult != null) {
                        recyclerView?.apply {
                            layoutManager = GridLayoutManager(this@RepoListActivitiy,1)
                            adapter = RepositoriesAdapter(itemList,this@RepoListActivitiy)
                        }
                    }
                    else{
                        Toast.makeText(this@RepoListActivitiy, "went wrong ", Toast.LENGTH_SHORT).show()
                    }

                    Log.d(TAG, "response: $response")
                }

                override fun onFailure(call: Call<Datum>, t: Throwable) {
                    Toast.makeText(this@RepoListActivitiy, "Something went wrong $t", Toast.LENGTH_SHORT).show()
                }
            })
        }else{
            Toast.makeText(applicationContext, "Please enter some message! ", Toast.LENGTH_SHORT).show()
        }
    }


    override fun onItemClick(position: Int) {
        val clickedItem = itemList[position]

        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("profile", clickedItem.owner?.avatarUrl)
        intent.putExtra("name", clickedItem.name)
        intent.putExtra("description", clickedItem.description)
        intent.putExtra("language", clickedItem.language)
        intent.putExtra("htmlUrl", clickedItem.htmlUrl)
        intent.putExtra("fullName", clickedItem.fullName)
        intent.putExtra("createdAt", clickedItem.createdAt)
        intent.putExtra("updatedAt", clickedItem.updatedAt)

        startActivity(intent)
    }

}

