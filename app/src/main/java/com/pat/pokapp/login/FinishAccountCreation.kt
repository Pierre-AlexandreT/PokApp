package com.pat.pokapp.login

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.pat.pokapp.MainActivity
import com.pat.pokapp.MyCallback

import com.pat.pokapp.R
import com.pat.pokapp.controler.UserController
import com.pat.pokapp.entity.User.User

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "fbUserId"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [FinishAccountCreation.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [FinishAccountCreation.newInstance] factory method to
 * create an instance of this fragment.
 */
class FinishAccountCreation : Fragment() {

    private lateinit var root: View

    private val userController = UserController()

    // TODO: Rename and change types of parameters
    private var fbUserId: String? = null
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            fbUserId = it.getString(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_finish_account_creation, container, false)

        val textInputPseudo = root.findViewById<EditText>(R.id.edit_text_pseudo)

        val finishAccountButton = root.findViewById<Button>(R.id.finish_account_creation)

        finishAccountButton.setOnClickListener {
            val user = User(null,textInputPseudo.text.toString(),fbUserId.toString())

            userController.addUser(object: MyCallback(){
                override fun onError(throwable: Throwable) {
                    Log.d("debugLogin", throwable.toString())
                }

                override fun onSuccess(value: User) {
                    val intent = Intent(root.context,MainActivity::class.java)
                    intent.putExtra("INTENT_ACCESS_TOKEN", fbUserId)
                    startActivity(intent)
                }
            },user)
        }


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
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @return A new instance of fragment FinishAccountCreation.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(fbUserId: String) =
            FinishAccountCreation().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, fbUserId)
                }
            }
    }
}
