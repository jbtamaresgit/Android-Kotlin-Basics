package com.example.navigationbetweensceens.adapter

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.accessibility.AccessibilityNodeInfo
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.navigationbetweensceens.DetailActivity
import com.example.navigationbetweensceens.R

class LetterAdapter : RecyclerView.Adapter<LetterAdapter.LetterViewHolder>(){
    private val list = ('A').rangeTo('Z').toList()

    class LetterViewHolder(val view: View) : RecyclerView.ViewHolder(view){
        val button = view.findViewById<Button>(R.id.button_item)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LetterViewHolder {
        val layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_view, parent, false)
        layout.accessibilityDelegate = WordAdapter
        return LetterViewHolder(layout)
    }

    override fun onBindViewHolder(holder: LetterViewHolder, position: Int) {
        val item = list.get(position)
        holder.button.text = item.toString()
        holder.button.setOnClickListener{
            val context = holder.view.context
            //creates a new activity called detail activity a parameter (index of a letter)
            //passed through intent
            /*
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(DetailActivity.LETTER, holder.button.text.toString())
            context.startActivity(intent)*/

            //allows you to google search the prefix of the button the user clicked
            val queryUrl: Uri = Uri.parse("${DetailActivity.SEARCH_PREFIX}${item}")
            val intent = Intent(Intent.ACTION_VIEW, queryUrl)
            context.startActivity(intent)
        }
    }

    companion object Accessibility : View.AccessibilityDelegate(){
        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        override fun onInitializeAccessibilityNodeInfo(host: View?, info: AccessibilityNodeInfo?) {
            super.onInitializeAccessibilityNodeInfo(host, info)
            val customString = host?.context?.getString(R.string.look_up_words)
            val customClick = AccessibilityNodeInfo.AccessibilityAction(
                AccessibilityNodeInfo.ACTION_CLICK,
                customString
            )
            info?.addAction(customClick)
        }
    }
}