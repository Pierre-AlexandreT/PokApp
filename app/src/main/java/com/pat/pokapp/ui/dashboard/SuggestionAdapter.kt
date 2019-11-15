package com.pat.pokapp.ui.dashboard

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pat.pokapp.R
import com.pat.pokapp.entity.PokemonName

class SuggestionAdapter(private val myContext: Context, private val list : ArrayList<String>): ListAdapter<PokemonName, ViewHolder>(DiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textViewName.text = list[position]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(myContext).inflate(R.layout.suggestion_item, parent, false))
    }


}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    val textViewName: TextView = view.findViewById(R.id.suggestion_textView)
}

class DiffCallback(): DiffUtil.ItemCallback<PokemonName>(){
    override fun areItemsTheSame(oldItem: PokemonName, newItem: PokemonName): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: PokemonName, newItem: PokemonName): Boolean {
        return oldItem.equals(newItem)
    }

}