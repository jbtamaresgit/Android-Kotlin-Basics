package com.example.navigationcomponent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.navigationcomponent.Adapter.LetterAdapter
import com.example.navigationcomponent.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private var isLinearLayout = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        recyclerView = binding.recyclerView
        chooseLayout()
    }

    private fun chooseLayout(){
        if(isLinearLayout){
            recyclerView.layoutManager = LinearLayoutManager(this)
        }
        else{
            recyclerView.layoutManager = GridLayoutManager(this, 4)
        }

        recyclerView.adapter = LetterAdapter()
    }

    private fun setIcon(menuItem: MenuItem?){
        if(menuItem == null)
            return
        menuItem.icon = if(isLinearLayout)
            ContextCompat.getDrawable(this, R.drawable.ic_linear_layout)
        else
            ContextCompat.getDrawable(this, R.drawable.ic_grid_layout)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.layout_menu, menu)
        val layoutButton = menu?.findItem(R.id.action_switch_layout)
        setIcon(layoutButton)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.action_switch_layout -> {
                isLinearLayout = !isLinearLayout
                chooseLayout()
                setIcon(item)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}