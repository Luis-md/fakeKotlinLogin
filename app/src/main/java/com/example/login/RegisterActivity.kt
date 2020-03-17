package com.example.login

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.widget.RadioButton
import android.widget.Toast
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
                if (radioGroup.getCheckedRadioButtonId() == -1) {
                    registerAlert("Selecione seu sexo")
                    return false
                } else if (passwordField.editText!!.text.toString().trim().length < 6) {
                   registerAlert("Sua senha precisa ter no mínimo 6 caracteres")
                    return false
                }
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

    private fun registerAlert(msg: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Erro no cadastro")
        builder.setMessage(msg)

        builder.setPositiveButton(android.R.string.ok) { _, _ ->
            Toast.makeText(applicationContext,
                android.R.string.ok, Toast.LENGTH_SHORT).show()
        }
        builder.show()
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
