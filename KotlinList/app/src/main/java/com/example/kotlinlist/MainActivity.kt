package com.example.kotlinlist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.kotlinlist.databinding.ActivityMainBinding
import com.example.kotlinlist.layoutActivity.GridListActivity
import com.example.kotlinlist.layoutActivity.HorizontalListActivity
import com.example.kotlinlist.layoutActivity.VerticalListActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var listIntent : Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.verticalBtn.setOnClickListener{
            launchVerticalLayout()
        }

        binding.horizontalBtn.setOnClickListener{
            launchHorizontalLayout()
        }

        binding.gridBtn.setOnClickListener{
            launchGridLayout()
        }

        /*
        val myDataset = dataSource().loadAffirmations()
        val recyclerView = findViewById<RecyclerView>(R.id.affirmations_recycler_view)
        recyclerView.adapter = ItemAdapter(this, myDataset)
        recyclerView.setHasFixedSize(true)*/
    }

    private fun launchGridLayout() {
        listIntent = Intent(this, GridListActivity::class.java)
        startActivity(listIntent)
    }

    private fun launchHorizontalLayout() {
        listIntent = Intent(this, HorizontalListActivity::class.java)
        startActivity(listIntent)
    }

    private fun launchVerticalLayout() {
        listIntent = Intent(this, VerticalListActivity::class.java)
        startActivity(listIntent)
    }
}