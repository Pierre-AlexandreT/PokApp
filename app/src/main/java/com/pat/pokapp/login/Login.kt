package com.pat.pokapp.login
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.facebook.CallbackManager
import com.pat.pokapp.R
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.facebook.FacebookCallback
import com.facebook.login.widget.LoginButton
import com.pat.pokapp.MainActivity
import com.facebook.AccessToken

class Login : AppCompatActivity() {

    private val callbackManager: CallbackManager = CallbackManager.Factory.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val loginButton: LoginButton = findViewById(R.id.login_button)
        //loginButton.
        //loginButton.setReadPermissions("email")
        // If using in a fragment

        // Callback registration
        loginButton.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                val intent = Intent(applicationContext,MainActivity::class.java)
                intent.putExtra("INTENT_ACCESS_TOKEN", loginResult.accessToken)
                startActivity(intent)
            }

            override fun onCancel() {
                Toast.makeText(applicationContext, "Annulation", Toast.LENGTH_SHORT).show()
            }

            override fun onError(exception: FacebookException) {
                Log.d("debugLogin", exception.toString())
            }
        })

        val accessToken = AccessToken.getCurrentAccessToken()
        val isLoggedIn = accessToken != null && !accessToken.isExpired
        if (isLoggedIn) {
            Log.d("debugLogin", "logged in")
            val intent = Intent(applicationContext,MainActivity::class.java)
            intent.putExtra("INTENT_ACCESS_TOKEN", accessToken.token)
            startActivity(intent)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }
}
