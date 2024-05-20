package com.example.desafio_30days

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class desafios_activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_desafios)




        val Home: Button = findViewById(R.id.Btn_Home)

        Home.setOnClickListener{
            val intent = Intent(this@desafios_activity, MainActivity::class.java)
            startActivity(intent)
        }

        var sharedPreferences = getSharedPreferences("PerguntaDiaria", MODE_PRIVATE)

        val usuario = intent.getStringExtra("USER_USUARIO")


        val PerguntaRecomendada: TextView = this.findViewById(R.id.TxtPergunta_Recomendada)
        val Sortear: Button = findViewById(R.id.Btn_Sortear)

        val desafios = listOf(
            "Desafio 1: Fazer um elogio do coração.",
            "Desafio 2: Comer juntos.",
            "Desafio 3: Assistir ao filme favorito do parceiro.",
            "Desafio 4: Dar um abraço.",
            "Desafio 5: Fazer atividade física juntos.",
            "Desafio 6: Cozinhar uma nova receita juntos.",
            "Desafio 7: Trocar mensagens inspiradoras durante o dia.",
            "Desafio 8: Brincar de pega pega no quintal.",
            "Desafio 9: Contar uma história valiosa.",
            "Desafio 10: Planejar uma noite de jogos.",
        )
        fun sortearDesafio() {
            val desafioAleatorio = desafios.random()
            PerguntaRecomendada.text = desafioAleatorio
        }


        sortearDesafio()

        Sortear.setOnClickListener {
            sortearDesafio()
        }

    }
}