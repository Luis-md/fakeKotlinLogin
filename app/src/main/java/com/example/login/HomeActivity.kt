package com.example.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.login.MainActivity.Companion.USERNAME_EXTRA
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setupUI()
        title = "Home"
    }

    fun setupUI() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val usernameLogged = intent.extras?.getString(USERNAME_EXTRA)
        username.text = "Bem vindo, $usernameLogged"
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


}
