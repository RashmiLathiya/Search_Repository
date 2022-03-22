package com.tailor.test_search_repos.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import com.tailor.test_search_repos.R
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_detail.ic_back

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val intent = intent

        val strProfile=getIntent().getStringExtra("profile")
        val strRepoName=intent.getStringExtra("name")
        val strRepoDetail=intent.getStringExtra("description")
        val strLanguage=intent.getStringExtra("language")
        val strHtmlUrl=intent.getStringExtra("htmlUrl")
        val strFullName=intent.getStringExtra("fullName")
        val strCreatedAt=intent.getStringExtra("createdAt")
        val strUpdatedAt=intent.getStringExtra("updatedAt")


        tvRepoName.text= strRepoName
        tvRepoDetail.text = "Description:" + strRepoDetail
        tvLanguage.text = "Language: "+strLanguage
        tvFullname.text = strFullName
        tvCreated.text = "Created at: " +strCreatedAt
        tvUpdated.text = "Updated at: " + strUpdatedAt

        Picasso.get().load(strProfile).into(ivProfile)

        rlLink.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(strHtmlUrl)))
        }

        ic_back.setOnClickListener {
            onBackPressed()
        }

    }

}