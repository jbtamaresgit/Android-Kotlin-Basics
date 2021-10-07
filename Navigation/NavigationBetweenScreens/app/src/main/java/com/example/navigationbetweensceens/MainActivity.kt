package com.example.navigationbetweensceens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.navigationbetweensceens.adapter.LetterAdapter
import com.example.navigationbetweensceens.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private var isVerticalLayout = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        recyclerView = binding.recyclerView
        chooseLayout()
        recyclerView.adapter = LetterAdapter()
    }

    //handles menu button
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.layout_menu, menu)
        //fetch the id of the menu item button under the layout_menu
        val layoutButton = menu?.findItem(R.id.action_switch_layout)
        setIcon(layoutButton)
        return true
    }

    //checks if the layout is in vertical and sets the icon according to it
    private fun setIcon(menuItem: MenuItem?){
        if(menuItem == null)
            return

        menuItem.icon =
            if(isVerticalLayout)
                ContextCompat.getDrawable(this, R.drawable.ic_list_grid)
            else
                ContextCompat.getDrawable(this, R.drawable.ic_list_vertical)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.action_switch_layout -> {
                //sets the opposite value of the current value of the isVerticalLayout
                isVerticalLayout = !isVerticalLayout
                //choose and sets the icon of the layout
                chooseLayout()
                setIcon(item)
                return true
            }
            //otherwise, do nothing and use the default handler
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun chooseLayout(){
        if(isVerticalLayout)
            recyclerView.layoutManager = LinearLayoutManager(this)
        else
            recyclerView.layoutManager = GridLayoutManager(this, 4)

        recyclerView.adapter = LetterAdapter()
    }
    //end handles menu button
}