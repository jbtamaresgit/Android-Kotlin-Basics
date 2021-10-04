package com.example.kotlinlist.layoutActivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.affirmations.adapter.ItemAdapter
import com.example.affirmations.const.Layout
import com.example.kotlinlist.databinding.ActivityHorizontalListBinding

class HorizontalListActivity : AppCompatActivity() {

    private lateinit var binding : ActivityHorizontalListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHorizontalListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.horizontalRecyclerView.adapter = ItemAdapter(
            applicationContext,
            Layout.HORIZONTAL
        )
        binding.horizontalRecyclerView.setHasFixedSize(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}