package com.tailor.test_search_repos.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tailor.test_search_repos.R
import kotlinx.android.synthetic.main.activity_start.*

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        /** val search_view = findViewById<EditText>(R.id.search_view)
        ivSearch.setOnClickListener {
            val intent = Intent(this, RepoListActivitiy::class.java)
            intent.putExtra("repositoryName", search_view.text.toString())
            startActivity(intent)
        }*/

        project.setOnClickListener {
            val intent = Intent(this, RepoListActivitiy::class.java)
            startActivity(intent)
        }
    }
}