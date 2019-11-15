package com.pat.pokapp.ui.friends

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pat.pokapp.R

class RecyclerViewAdapter (private val items : ArrayList<String>, private val myContext: Context):
    ListAdapter<ArrayList<String>, RecyclerViewAdapter.ViewHolder>(DiffCallback()){

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d("debugRecyclerView",items[position])
        holder.textViewName.text = items[position]
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(myContext).inflate(R.layout.friends_listview_item, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        val textViewName: TextView = view.findViewById(R.id.textView_friend_name)
    }

    class DiffCallback(): DiffUtil.ItemCallback<ArrayList<String>>(){
        override fun areItemsTheSame(oldItem: ArrayList<String>, newItem: ArrayList<String>): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ArrayList<String>, newItem: ArrayList<String>): Boolean {
            return oldItem.equals(newItem)
        }

    }


}