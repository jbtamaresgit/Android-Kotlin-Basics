package com.example.navigationcomponent.Adapter

import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.accessibility.AccessibilityNodeInfo
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.navigationcomponent.LetterListFragment
import com.example.navigationcomponent.LetterListFragmentDirections
import com.example.navigationcomponent.R

class LetterAdapter : RecyclerView.Adapter<LetterAdapter.LetterViewHolder>() {

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
            /*
val context = holder.view.context
val intent = Intent(context, LetterListFragment::class.java)
intent.putExtra(LetterListFragment.LETTER, holder.button.text.toString())
context.startActivity(intent)*/
            //LetterListFragmentDirections lets you refer to all possible navigation paths from LetterListFragment
            //actionletterListFragmentToWordListFragment is a function that was created to specify an action to navigate
            //to the wordListFragment, it also allows you to pass arguments you specified in nav_graph
            val action = LetterListFragmentDirections.actionLetterListFragmentToWordListFragment(letter = holder.button.text.toString())
            holder.view.findNavController().navigate(action)
        }
    }
    companion object Accessibility : View.AccessibilityDelegate(){
        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        override fun onInitializeAccessibilityNodeInfo(host: View?, info: AccessibilityNodeInfo?) {
            super.onInitializeAccessibilityNodeInfo(host, info)

            val customString = host?.context?.getString(R.string.look_up_words)
            val customClick = AccessibilityNodeInfo.AccessibilityAction(AccessibilityNodeInfo.ACTION_CLICK,
            customString)
            info?.addAction(customClick)
        }
    }
}