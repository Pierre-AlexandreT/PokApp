package com.pat.pokapp.ui.dashboard

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.pat.pokapp.R
import com.pat.pokapp.MyCallback
import com.pat.pokapp.controler.PokemonController
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.pat.pokapp.entity.*

class DashboardFragment : Fragment() {

    private lateinit var root: View

    private val pokemonController = PokemonController()

    private lateinit var dashboardViewModel: DashboardViewModel
    private lateinit var pokemonRecyclerView: RecyclerView
    private lateinit var searchAutoCompleteTextView: AutoCompleteTextView
    private lateinit var searchBtn: ImageButton
    private var array: ArrayList<String> = arrayListOf("text1", "text2", "text3", "text4")
    private lateinit var progressBar: ProgressBar
    private lateinit var pokemonRecyclerViewAdapter: PokemonRecyclerViewAdapter
    private var listPokemons = arrayListOf<Pokemon>()
    private var listPokemonName = arrayListOf<String>()
    private var listener: PokemonDetailInterface? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProviders.of(this).get(DashboardViewModel::class.java)
        root = inflater.inflate(R.layout.fragment_dashboard, container, false)
//        val textView: TextView = root.findViewById(R.id.text_dashboard)
//        dashboardViewModel.text.observe(this, Observer {
//            textView.text = it
//        })

        searchAutoCompleteTextView = root.findViewById<AutoCompleteTextView>(R.id.searchTextView)

        searchBtn = root.findViewById(R.id.searchButton)

        progressBar = root.findViewById(R.id.dashboar_progressbar)

        pokemonRecyclerView = root.findViewById(R.id.dashboardGrid)

        pokemonRecyclerView.layoutManager = GridLayoutManager(root.context, 2, LinearLayoutManager.VERTICAL, false)
        pokemonRecyclerViewAdapter = PokemonRecyclerViewAdapter(root.context, object : OnClickRow {
            override fun onClic(pokemon: String) {
                listener?.pokemonClicked(pokemon)
            }


        })

        pokemonRecyclerView.adapter = pokemonRecyclerViewAdapter
        displaySearchSuggestion()

        searchBtn.setOnClickListener(View.OnClickListener {
            progressBar.visibility = View.VISIBLE
            displaySearchedPokemon(searchAutoCompleteTextView.text.toString())
        })

        displayPokemonList()


        return root

    }

    fun displayPokemonList() {
        progressBar.visibility = View.VISIBLE
        pokemonController.getPokeApiPokemonList(object : MyCallback() {
            override fun onError(throwable: Throwable) {
                progressBar.visibility = View.GONE
                Log.d("debugDashboard", throwable.toString())
            }

            override fun onSuccess(value: Pokemons) {

                value.results.let {
                    listPokemons.clear()
                    listPokemons.addAll(it!!.asIterable())
                    var i = 0;
                    for (item in listPokemons) {
                        item.name?.let { it1 -> listPokemonName.add(it1) }
                    }
                    pokemonRecyclerViewAdapter.submitList(listPokemons)
                    pokemonRecyclerViewAdapter.notifyDataSetChanged()
                }


                progressBar.visibility = View.GONE

            }
        })
    }

    fun displaySearchSuggestion() {

        val adapterPokemonName =
            ArrayAdapter(root.context, android.R.layout.simple_list_item_1, listPokemonName)

        searchAutoCompleteTextView.setAdapter(adapterPokemonName)

        // Set the minimum number of characters, to show suggestions
        searchAutoCompleteTextView.setThreshold(3)

        searchAutoCompleteTextView.setOnItemClickListener { parent, _, position, _ ->
            // displaySearchedPokemon(parent.getItemAtPosition(position).toString())
        }
    }

    fun displaySearchedPokemon(searchTerm: String) {
        progressBar.visibility = View.VISIBLE
        pokemonController.searchPokemons(object : MyCallback() {
            override fun onError(throwable: Throwable) {
                progressBar.visibility = View.GONE
                Log.d("debugDashboard", throwable.toString())
            }

            override fun onSuccess(value: Pokemon) {

                value.let {
                    listPokemons.clear()
                    listPokemons.add(value)
                    pokemonRecyclerViewAdapter.submitList(listPokemons)
                    pokemonRecyclerViewAdapter.notifyDataSetChanged()
                }


                progressBar.visibility = View.GONE

            }
        }, searchTerm)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is PokemonDetailInterface) {
            listener = context
        } else {
            throw RuntimeException("$context must implement PokemonDetailInterface")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }
}

interface PokemonDetailInterface {
    fun pokemonClicked(pokemonName: String)
}