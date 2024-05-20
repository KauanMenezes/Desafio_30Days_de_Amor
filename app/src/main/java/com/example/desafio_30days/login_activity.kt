package com.example.desafio_30days

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isInvisible
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database

class login_activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val Usuario: EditText = findViewById(R.id.Edit_Usuario)
        Usuario.isInvisible = false
        val Senha: EditText = findViewById(R.id.Edit_Senha)
        Senha.isInvisible = false
        val Voltar: Button = findViewById(R.id.Btn_Voltar)
        val Logar: Button = findViewById(R.id.Btn_Logar)

        Voltar.setOnClickListener{
            val intent = Intent(this@login_activity, MainActivity::class.java)
            startActivity(intent)
        }

        Logar.setOnClickListener {
            val edit_usuario = Usuario.text.toString()
            val edit_senha = Senha.text.toString()

            if (edit_usuario.isNotEmpty() && edit_senha.isNotEmpty()) {
                val database = Firebase.database
                val usersRef = database.getReference("users")

                usersRef.addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        var userFound = false
                        for (userSnapshot in snapshot.children) {
                            val user = userSnapshot.getValue(UserLogin::class.java)
                            if (user != null && user.usuario == edit_usuario) {
                                userFound = true
                                Log.d(ContentValues.TAG, "Login successful")
                                val intent = Intent(
                                    this@login_activity, MainActivity::class.java
                                ).apply {
                                    putExtra("USER_USUARIO", user.usuario)
                                    putExtra("USER_SENHA", user.senha)
                                }
                                startActivity(intent)
                                break
                            }
                        }
                        if (!userFound) {
                            Toast.makeText(
                                baseContext, "USUARIO ou SENHA INCORRETA ",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        Log.e(ContentValues.TAG, "Database error: ${error.message}")
                        Toast.makeText(
                            baseContext, "ERRO de CONEXAO com o BANCO ",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                })
            } else {
                Toast.makeText(
                    baseContext, "Preencha os campos vazios ",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    }
}
data class UserLogin(val usuario: String, val senha: String)