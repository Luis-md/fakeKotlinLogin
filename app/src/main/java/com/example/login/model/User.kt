package com.example.login.model

import java.io.Serializable

class User (
    val nome: String,
    val sexo: String,
    val email: String,
    val senha: String,
    val telefone: String,
    val disciplina: String,
    val turma: String
) : Serializable

//obj é passavel entre as telas -> Serializable