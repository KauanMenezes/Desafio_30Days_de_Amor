package com.example.desafio_30days

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isInvisible
import com.google.firebase.database.FirebaseDatabase


class perfil_activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)


        val Hobby: EditText = findViewById(R.id.Edit_Hobby)
        Hobby.isInvisible = false
        val Comida: EditText = findViewById(R.id.Edit_Comida)
        Comida.isInvisible = false
        val Sonho: EditText = findViewById(R.id.Edit_Sonho)
        Sonho.isInvisible = false
        val Sentimento: EditText = findViewById(R.id.Edit_Sentimento)
        Sentimento.isInvisible = false
        val Complemento: EditText = findViewById(R.id.Edit_Complemento)
        Complemento.isInvisible = false

        val Cadastrar: Button = findViewById(R.id.Btn_Cadastrar)
        val Voltar: Button = findViewById(R.id.Btn_Voltar)

        Voltar.setOnClickListener{
            val intent = Intent(this@perfil_activity, MainActivity::class.java)
            startActivity(intent)
        }

        Cadastrar.setOnClickListener {
            val edit_hobby = Hobby.text.toString()
            val edit_comida = Comida.text.toString()
            val edit_sonho = Sonho.text.toString()
            val edit_sentimento = Sentimento.text.toString()
            val edit_complemento = Complemento.text.toString()

            if (edit_hobby.isNotEmpty() && edit_comida.isNotEmpty()&& edit_sonho.isNotEmpty()&& edit_sentimento.isNotEmpty()&& edit_complemento.isNotEmpty()) {
                val database = FirebaseDatabase.getInstance()
                val userId = database.reference.push().key ?: return@setOnClickListener


                val user = CadastrarP(edit_hobby, edit_comida, edit_sonho, edit_sentimento, edit_complemento)
                val usersRef = database.getReference("users/$userId")

                usersRef.setValue(user).addOnCompleteListener {
                        task ->
                    if (task.isSuccessful) {
                        val intent = Intent(this@perfil_activity, desafios_activity::class.java).apply {
                            putExtra("USER_ID", userId)
                            putExtra("USER_HOBBY", edit_hobby)
                            putExtra("USER_COMIDA", edit_comida)
                            putExtra("USER_SONHO", edit_sonho)
                            putExtra("USER_SENTIMENTO", edit_sentimento)
                            putExtra("USER_COMPLEMENTO", edit_complemento)
                        }
                        startActivity(intent)

                        Toast.makeText(
                            baseContext, "Sucesso na Criação de Perfil",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(
                            baseContext, "Erro na Criação de Perfil ",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                }
            }
            else{
                Toast.makeText(
                    baseContext, "Preencha os campos vazios ",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    }
}
data class CadastrarP(val Hobby : String, val Comida : String, val Sonho : String,  val Sentimento : String, val Complemento : String)