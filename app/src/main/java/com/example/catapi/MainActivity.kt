package com.example.catapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.activity.viewModels




class MainActivity : AppCompatActivity() {

    private val itemAdapter = CatsAdapter(this)
    private val catViewModel by viewModels<CatViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val progress = findViewById<ProgressBar>(R.id.progress_bar)
        progress.visibility = View.INVISIBLE

        val recyclerView = findViewById<RecyclerView>(R.id.catslist)
        recyclerView.layoutManager = GridLayoutManager(this,2)
        recyclerView.adapter = itemAdapter

        catViewModel.items.observe(this, Observer {
            it ?: return@Observer
            itemAdapter.addItems(it)
        })

    }

}
