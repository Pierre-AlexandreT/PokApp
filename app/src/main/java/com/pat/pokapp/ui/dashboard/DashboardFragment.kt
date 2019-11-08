package com.pat.pokapp.ui.dashboard

import android.os.Bundle
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


class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel
    private lateinit var gridView: GridView
    private lateinit var searchTextView: TextView
    private lateinit var searchBtn: ImageButton
    private var array: ArrayList<String> = arrayListOf("text1", "text2", "text3", "text4")
    private lateinit var progressBar: ProgressBar
    private lateinit var gridViewAdapter: GridViewAdapter
    private lateinit var listPokemons: ArrayList<Pokemon>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProviders.of(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        //val textView: TextView = root.findViewById(R.id.text_dashboard)
//        dashboardViewModel.text.observe(this, Observer {
//            textView.text = it
//        })it

        searchTextView = root.findViewById(R.id.searchTextView)

        searchBtn = root.findViewById(R.id.searchButton)

        progressBar = root.findViewById(R.id.dashboar_progressbar)

        gridView = root.findViewById(R.id.dashboardGrid)



        val pokemonController = PokemonController();

        pokemonController.getPokemons(object : MyCallback() {
            override fun onSuccess(value: Pokemons) {
                listPokemons = value.results!!

                listPokemons.let {

                    gridViewAdapter = GridViewAdapter(listPokemons, root.context)
                    gridView.adapter = gridViewAdapter
                }

                progressBar.visibility = View.INVISIBLE
                Log.d("debug", "afficher")


//                gridView.onItemClickListener =
//                    AdapterView.OnItemClickListener { adapterView, view, i, l ->
//                        for (j in 0 until speciesList.get(i).getPeople().size()) {
//                            val p = Pattern.compile("\\d+")
//                            val m = p.matcher(speciesList.get(i).getPeople().get(j))
//                            var result = ""
//                            while (m.find()) {
//                                result += m.group()
//                            }
//
//                            controller.getOnePeople(result) //string to int @todo je suis la
//                        }
//                    }
            }

            override fun onError(throwable: Throwable) {
                Log.d("debug", throwable.toString())
            }
        })

        searchBtn.setOnClickListener(View.OnClickListener {
            progressBar.visibility = View.VISIBLE
            pokemonController.getApiPokemon(object :MyCallback() {
                override fun onError(throwable: Throwable) {
                    Log.d("debug", throwable.toString())
                    progressBar.visibility = View.INVISIBLE
                }

                override fun onSuccess(value: Pokemon) {
                    listPokemons= ArrayList<Pokemon>()
                    listPokemons.add(value)

                    listPokemons.let {
                        gridViewAdapter.notifyDataSetChanged()
                    }

                    progressBar.visibility = View.INVISIBLE
                    Log.d("debug", "afficher")


//                gridView.onItemClickListener =
//                    AdapterView.OnItemClickListener { adapterView, view, i, l ->
//                        for (j in 0 until speciesList.get(i).getPeople().size()) {
//                            val p = Pattern.compile("\\d+")
//                            val m = p.matcher(speciesList.get(i).getPeople().get(j))
//                            var result = ""
//                            while (m.find()) {
//                                result += m.group()
//                            }
//
//                            controller.getOnePeople(result) //string to int @todo je suis la
//                        }
//                    }
                }


            },searchTextView.text.toString())
        })


        return root

    }
}