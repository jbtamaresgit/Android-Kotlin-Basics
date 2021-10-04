package com.example.kotlinlist.layoutActivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.affirmations.adapter.ItemAdapter
import com.example.affirmations.const.Layout
import com.example.kotlinlist.databinding.ActivityGridListBinding

class GridListActivity : AppCompatActivity() {
    private lateinit var binding : ActivityGridListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGridListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.gridListRecyclerView.layoutManager = GridLayoutManager(this, 2)
        binding.gridListRecyclerView.adapter = ItemAdapter(
            applicationContext,
            Layout.GRID
        )
        binding.gridListRecyclerView.setHasFixedSize(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

}