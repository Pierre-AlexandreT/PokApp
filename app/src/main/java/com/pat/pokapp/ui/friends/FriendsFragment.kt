package com.pat.pokapp.ui.friends

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pat.pokapp.R

class FriendsFragment : Fragment() {

    private lateinit var friendsViewModel: FriendsViewModel

    private lateinit var listView: RecyclerView

    private val array:ArrayList<String> = arrayListOf("user1","user2","user3","user4")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        friendsViewModel =
            ViewModelProviders.of(this).get(FriendsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_friends, container, false)
        val textView: TextView = root.findViewById(R.id.text_notifications)
        friendsViewModel.text.observe(this, Observer {
            textView.text = it
        })

        listView = root.findViewById(R.id.friendsFragmentList)
        listView.layoutManager = LinearLayoutManager(root.context)

        listView.adapter = RecyclerViewAdapter(array,root.context)

        return root
    }
}