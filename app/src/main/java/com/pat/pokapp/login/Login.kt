package com.pat.pokapp.login

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.facebook.CallbackManager
import com.pat.pokapp.R
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.facebook.FacebookCallback
import com.facebook.login.widget.LoginButton
import com.pat.pokapp.MainActivity
import com.facebook.AccessToken
import com.pat.pokapp.MyCallback
import com.pat.pokapp.PokemonDetail
import com.pat.pokapp.controler.PokemonController
import com.pat.pokapp.controler.UserController
import com.pat.pokapp.entity.User.User
import kotlinx.android.synthetic.main.activity_login.*

class Login : AppCompatActivity(),FinishAccountCreation.OnFragmentInteractionListener {
    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    private val callbackManager: CallbackManager = CallbackManager.Factory.create()

    private val userController = UserController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val loginButton: LoginButton = findViewById(R.id.login_button)

        //loginButton.
        //loginButton.setReadPermissions("email")
        // If using in a fragment

        // Callback registration
        //displayAdditionnalElement("sdhsglkjsdglkj")
        loginButton.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {

                displayAdditionnalElement(loginResult.accessToken.userId)


                //launchActivity(loginResult.accessToken.token)
            }

            override fun onCancel() {
                Toast.makeText(applicationContext, "Annulation", Toast.LENGTH_SHORT).show()
            }

            override fun onError(exception: FacebookException) {
                Log.d("debugLogin", exception.toString())
            }
        })

//        val accessToken = AccessToken.getCurrentAccessToken()
//        if (accessToken != null && !accessToken.isExpired) {
//            launchActivity(accessToken.token)
//        }
        launchActivity("kjshdkgf")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun launchActivity(fbUserId: String){
        val intent = Intent(applicationContext,MainActivity::class.java)
        intent.putExtra("INTENT_ACCESS_TOKEN", fbUserId)
        startActivity(intent)
    }

    private fun displayAdditionnalElement(fbUserId: String){
        userController.userIsRegistered(object : MyCallback() {
            override fun onError(throwable: Throwable) {
                Log.d("debugLogin", throwable.toString())
            }

            override fun onSuccess(value: User) {

                if(value.pseudo == null) {
                    val fragmentTransaction = supportFragmentManager.beginTransaction()
                    fragmentTransaction.add(R.id.fragment_finish_account, FinishAccountCreation.newInstance(fbUserId))
                    fragmentTransaction.addToBackStack(null)
                    fragmentTransaction.commit()
                }else{
                    launchActivity(fbUserId)
                }
            }
        }, fbUserId)
    }

}
