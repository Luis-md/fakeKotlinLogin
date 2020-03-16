package com.example.login

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.RadioButton
import com.example.login.MainActivity.Companion.USER_RESULT
import com.example.login.model.User
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    companion object {
        const val USER_EXTRA = "userObj"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        title = "Inscrever-se"
        setupUI()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            //R -> objeto do res
            R.id.registrarBtn -> {
                val user = createUser()
                sendUser(user)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.register_menu, menu)
        return true
    }

    fun setupUI() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    fun createUser(): User {
        return User(
            nomeField.editText!!.text.toString(),
            findViewById<RadioButton>(radioGroup.checkedRadioButtonId).text.toString(),
            emailField.editText!!.text.toString(),
            passwordField.editText!!.text.toString(),
            foneField.editText!!.text.toString(),
            disciplinaField.editText!!.text.toString(),
            turmaField.editText!!.text.toString()
        )
    }

    fun sendUser(user: User) {
        val userIntent = Intent()
        userIntent.putExtra(USER_EXTRA, user)
        setResult(Activity.RESULT_OK, userIntent)
        //startActivity(userIntent)
        finish()
        //tira a tela da stack
    }
}
