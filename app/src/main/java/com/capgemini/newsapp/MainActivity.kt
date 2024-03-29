package com.capgemini.newsapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    lateinit var rView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rView = findViewById(R.id.rView)
        rView.layoutManager = LinearLayoutManager(this)

        CoroutineScope(Dispatchers.Default).launch {
            val result = NewsInterface.getInstance().getTopHeadlines(
                "in",
                "00b141264a9b4eb38721698f7cbf8879")
            Log.d("MainActivity", "No. of articles: ${result.articles.count()}")

            withContext(Dispatchers.Main){
                rView.adapter = NewsAdapter(result.articles){
                    if(result.articles[it].url!=null) {
                        val webIntent =
                            Intent(Intent.ACTION_VIEW, Uri.parse(result.articles[it].url))
                        startActivity(webIntent)
                        Log.d("MainActivity", "Browser Opening")
                    }
                }
            }
        }
    }
}