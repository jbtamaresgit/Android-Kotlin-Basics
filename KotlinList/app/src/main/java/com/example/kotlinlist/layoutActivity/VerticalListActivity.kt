package com.example.kotlinlist.layoutActivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.affirmations.adapter.ItemAdapter
import com.example.affirmations.const.Layout
import com.example.kotlinlist.databinding.ActivityVerticalListBinding

class VerticalListActivity : AppCompatActivity() {
    private lateinit var binding : ActivityVerticalListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVerticalListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.verticalRecyclerView.adapter = ItemAdapter(
            applicationContext,
            Layout.VERTICAL
        )

        binding.verticalRecyclerView.setHasFixedSize(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}