package com.pat.pokapp.ui.dashboard

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pat.pokapp.R
import com.pat.pokapp.entity.Pokemon
import com.squareup.picasso.Picasso

class PokemonRecyclerViewAdapter(private val myContext: Context, val listener: OnClickRow) : ListAdapter<Pokemon, PokemonRecyclerViewAdapter.ViewHolder>(
    DiffCallbackGridViewAdapter()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //val view = LayoutInflater.from(myContext).inflate(R.layout.dashboard_listview_item, parent, false)
        val v = LayoutInflater.from(myContext).inflate(R.layout.dashboard_listview_item, parent, false)



        return ViewHolder(v)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d("debugRecyclerview",getItem(position).name )
        holder.label.text = getItem(position).name
        Picasso.get().load(getItem(position).sprites).into(holder.image)

        holder.itemView.setOnClickListener{

            listener.onClic(getItem(position).name!!)
        }
    }



    class ViewHolder (view: View): RecyclerView.ViewHolder(view) {
        val label: TextView = view.findViewById(R.id.textView_listView_item) as TextView
        val image: ImageView = view.findViewById(R.id.dashboard_pokemon_image) as ImageView

    }

    class DiffCallbackGridViewAdapter: DiffUtil.ItemCallback<Pokemon>(){
        override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
            return oldItem.equals(newItem)
        }

    }


}

interface OnClickRow{
    fun onClic(pokemon: String)
}