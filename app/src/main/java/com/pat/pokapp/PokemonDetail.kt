package com.pat.pokapp

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.pat.pokapp.controler.PokemonController
import com.pat.pokapp.entity.Pokemon
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_pokemon_detail.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [PokemonDetail.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [PokemonDetail.newInstance] factory method to
 * create an instance of this fragment.
 */
class PokemonDetail : Fragment() {

    private lateinit var root: View
    private val pokemonController = PokemonController()

    private var pokemonName: String? = null
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            pokemonName = it.getString(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?




    ): View? {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_pokemon_detail, container, false)
        val textViewPokemonName = root.findViewById<TextView>(R.id.textView_pokemon_name)
        textViewPokemonName.text=pokemonName

        pokemonController.getApiPokemon(object : MyCallback() {
            override fun onError(throwable: Throwable) {
                Log.d("debugDashboard", throwable.toString())
            }

            override fun onSuccess(value: Pokemon) {
                textViewPokemonName.text=pokemonName
                Picasso.get().load(value.url).into(dashboard_pokemon_detail_image)
            }
        }, pokemonName!!)





        return root
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        @JvmStatic
        fun newInstance(pokemonName: String) =
            PokemonDetail().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, pokemonName)
                }
            }
    }
}
