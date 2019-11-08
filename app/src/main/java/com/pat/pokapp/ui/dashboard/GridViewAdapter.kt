package com.pat.pokapp.ui.dashboard

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.pat.pokapp.R
import com.pat.pokapp.entity.Pokemon
import com.squareup.picasso.Picasso

class GridViewAdapter(private val list : ArrayList<Pokemon>, private val myContext: Context) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val view: View?
        val vh: ListRowHolder
        if (convertView == null) {
            view = LayoutInflater.from(myContext).inflate(R.layout.dashboard_listview_item, parent, false)
            vh = ListRowHolder(view)
            view.tag = vh
        } else {
            view = convertView
            vh = view.tag as ListRowHolder
        }

        vh.label.text = list[position].name
        Picasso.get().load(list[position].url).into(vh.image)


        return view
    }

    override fun getItem(p0: Int): Any {
        return list[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int {
        return list.size
    }




}

private class ListRowHolder(row: View?) {
    val label: TextView = row?.findViewById(R.id.textView_listView_item) as TextView
    val image: ImageView = row?.findViewById(R.id.dashboard_pokemon_image) as ImageView

}