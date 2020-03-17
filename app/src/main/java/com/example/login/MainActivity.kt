package com.example.login

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.login.RegisterActivity.Companion.USER_EXTRA
import com.example.login.model.User
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val USER_RESULT = 1
        const val USERNAME_EXTRA = "username"
    }

    private var usuario: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupUI()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode) {
            USER_RESULT -> {
                if(resultCode == Activity.RESULT_OK) {
                    val user = data?.extras?.getSerializable(USER_EXTRA) as? User
                    usuario = user
                }
            }
        }
    }

    fun setupUI() {
        registrarButton.setOnClickListener {
            //chama activity esperando um resultado
            startActivityForResult(Intent(this, RegisterActivity::class.java), USER_RESULT)
        }

        loginButton.setOnClickListener {


            if (loginField.editText!!.text.toString() == "" || passwordField.editText!!.text.toString() == "") {
                loginAlert("Preencha os campos de usuário e senha")
            } else if (usuario == null) {
                loginAlert("Campo de usuário ou senha inválido")
            } 

            usuario?.let {
                if (it.email == loginField.editText!!.text.toString() && it.senha == passwordField.editText!!.text.toString()) {
                    val usernameIntent = Intent(this, HomeActivity::class.java)
                    usernameIntent.putExtra(USERNAME_EXTRA, it.nome)
                    startActivity(usernameIntent)
                    finish()
                } else {
                    loginAlert("Campo de usuário ou senha inválido")
                }
            }
        }
    }

    private fun loginAlert(msg: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Erro no login")
        builder.setMessage(msg)

        builder.setPositiveButton(android.R.string.ok) { _, _ ->
            Toast.makeText(applicationContext,
                android.R.string.ok, Toast.LENGTH_SHORT).show()
        }
        builder.show()
    }
}
