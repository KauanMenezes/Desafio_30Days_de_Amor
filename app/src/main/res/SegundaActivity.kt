package com.example.desafio_30days

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class SegundaActivity : AppCompatActivity() {
    private lateinit var sair: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.segunda_activity)

        sair = findViewById(R.id.Btn_Voltar)
        sair.setOnClickListener { onClickSair() }
    }

    private fun onClickSair() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}