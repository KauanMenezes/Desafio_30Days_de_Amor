package com.example.desafio_30days

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isInvisible
import com.google.firebase.Firebase
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.database

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val database = Firebase.database
        val myRef = database.getReference("message")
        myRef.setValue("to voando altooo, sigo na minha luta e trabalhando dobrado...")

        val Cadastrar: Button = findViewById(R.id.Btn_Cadastrar)
        val Usuario: EditText = findViewById(R.id.Edit_Usuario)
        Usuario.isInvisible = false
        val Senha: EditText = findViewById(R.id.Edit_Senha)
        Senha.isInvisible = false

        val Avancar: Button = findViewById(R.id.Btn_Avancar)

        Avancar.setOnClickListener {
            val intent = Intent(this@MainActivity, login_activity::class.java)
            startActivity(intent)
        }


        Cadastrar.setOnClickListener {
            val edit_usuario = Usuario.text.toString()
            val edit_senha = Senha.text.toString()

            if (edit_usuario.isNotEmpty() && edit_senha.isNotEmpty()) {
                val database = FirebaseDatabase.getInstance()
                val userId = database.reference.push().key ?: return@setOnClickListener


                val user = Cadastrar(edit_usuario, edit_senha)
                val usersRef = database.getReference("users/$userId")

                usersRef.setValue(user).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val intent =
                            Intent(this@MainActivity, perfil_activity::class.java).apply {
                                putExtra("USER_ID", userId)
                                putExtra("USER_USUARIO", edit_usuario)
                                putExtra("USER_PASSWORD", edit_senha)
                            }
                        startActivity(intent)

                        Toast.makeText(
                            baseContext, "Sucesso no Cadastro",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(
                            baseContext, "Erro no Cadastro ",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                }
            } else {
                Toast.makeText(
                    baseContext, "Preencha os campos vazios ",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }
    }
}
data class Cadastrar(val usuario: String, val senha: String)


