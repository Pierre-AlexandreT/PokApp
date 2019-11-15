package com.pat.pokapp.ui.dashboard

import android.content.Context
import android.inputmethodservice.Keyboard
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.pat.pokapp.entity.Pokemon
import com.pat.pokapp.R
import com.pat.pokapp.MyCallback
import com.pat.pokapp.controler.PokemonController
import com.pat.pokapp.entity.Pokemons
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
import com.pat.pokapp.entity.PokemonName

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
    private var listPokemons: MutableList<Pokemon> = mutableListOf()
    private lateinit var listPokemonName: ArrayList<String>
    private var listener: PokemonDetailInterface? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProviders.of(this).get(DashboardViewModel::class.java)
        root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        //val textView: TextView = root.findViewById(R.id.text_dashboard)
//        dashboardViewModel.text.observe(this, Observer {
//            textView.text = it
//        })it

        searchAutoCompleteTextView = root.findViewById(R.id.searchTextView)

        searchBtn = root.findViewById(R.id.searchButton)

        progressBar = root.findViewById(R.id.dashboar_progressbar)

        pokemonRecyclerView = root.findViewById(R.id.dashboardGrid)

        pokemonRecyclerView.layoutManager = GridLayoutManager(root.context, 2, LinearLayoutManager.VERTICAL, false)
        pokemonRecyclerViewAdapter = PokemonRecyclerViewAdapter(root.context, object : OnClickRow{
            override fun onClic(pokemonName: String) {
                listener?.pokemonClicked(pokemonName)
            }


        })

        pokemonRecyclerView.adapter = pokemonRecyclerViewAdapter

        displayPokemonList()

        displaySearchSuggestion()


        searchBtn.setOnClickListener(View.OnClickListener {
            progressBar.visibility = View.VISIBLE
            displaySearchedPokemon(searchAutoCompleteTextView.text.toString())
        })


        return root

    }

    fun displayPokemonList() {
        pokemonController.getPokemons(object : MyCallback() {
            override fun onSuccess(value: Pokemons) {
                listPokemons.clear()

                value.results.let {
                    listPokemons.addAll(it!!.asIterable())

                    pokemonRecyclerViewAdapter.submitList(listPokemons)
                }


                progressBar.visibility = View.GONE
            }

            override fun onError(throwable: Throwable) {
                Log.d("debugDashboard", throwable.toString())
            }
        })
    }

    fun displaySearchSuggestion() {
        pokemonController.getApiPokemonName(object : MyCallback() {
            override fun onError(throwable: Throwable) {
                progressBar.visibility = View.INVISIBLE
            }

            override fun onSuccess(value: PokemonName) {
                listPokemonName = value.results!!

                val adapterPokemonName =
                    ArrayAdapter(root.context, android.R.layout.simple_list_item_1, listPokemonName)

                searchAutoCompleteTextView.setAdapter(adapterPokemonName)

                // Set the minimum number of characters, to show suggestions
                searchAutoCompleteTextView.setThreshold(3)

                searchAutoCompleteTextView.setOnItemClickListener { parent, _, position, _ ->
                    displaySearchedPokemon(parent.getItemAtPosition(position).toString())
                }

            }
        })
    }

    fun displaySearchedPokemon(pokemonName: String) {
        pokemonController.getApiPokemon(object : MyCallback() {
            override fun onError(throwable: Throwable) {
                Log.d("debugDashboard", throwable.toString())
            }

            override fun onSuccess(value: Pokemon) {
                listPokemons.clear()
                listPokemons.add(value)
                pokemonRecyclerViewAdapter.submitList(listPokemons)
                pokemonRecyclerViewAdapter.notifyDataSetChanged()
            }
        }, pokemonName)
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