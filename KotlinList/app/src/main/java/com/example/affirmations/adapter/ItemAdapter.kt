package com.example.affirmations.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.affirmations.const.Layout
import com.example.affirmations.data.dataSource
import com.example.kotlinlist.R

//gets the context and the source of the data
class ItemAdapter(private val context: Context?, private val layout: Int) :
    RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    private val myDataset = dataSource().loadAffirmations()

    //ViewHolder() represents a single list item in RecyclerView. Holds references to the individual views
    //within a list item layout. Initialises the list item UI components
    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view!!){
        val textView: TextView = view.findViewById(R.id.item_titleTextView)
        val imageView: ImageView = view.findViewById(R.id.item_image)
    }

    //parent parameter -- recyclerview
    //viewType parameter used when there are multiple item view types in the same recycler view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        var activityLayout : Int = R.layout.list_item_vertical
        when(layout){
            1 -> activityLayout = R.layout.list_item_vertical
            2 -> activityLayout = R.layout.list_item_horizontal
            3 -> activityLayout = R.layout.list_item_grid
        }

        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(activityLayout, parent, false)

        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = myDataset[position]
        holder.textView.text = context?.resources?.getString(item.stringResourceId)
        holder.imageView.setImageResource(item.imageResourceId)
    }

    override fun getItemCount(): Int {
        return myDataset.size
    }
}