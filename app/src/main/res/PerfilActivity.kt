package com.example.desafio_30days

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class PerfilActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.perfil_activity)



        val Voltar: Button = findViewById(R.id.Btn_Voltar)

        Voltar.setOnClickListener{
            val intent = Intent(this@PerfilActivity, MainActivity::class.java)
            startActivity(intent)


        }
    }
}
