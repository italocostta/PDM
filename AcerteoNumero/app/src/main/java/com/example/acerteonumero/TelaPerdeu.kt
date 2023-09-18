package com.example.acerteonumero

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class TelaPerdeu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_perdeu)

        val mensagemDerrota = "Você perdeu! Tente novamente."
        val mensagemTextView = findViewById<TextView>(R.id.mensagemDerrota)
        mensagemTextView.text = mensagemDerrota

        val botaoReiniciar = findViewById<Button>(R.id.botaoReiniciar)
        botaoReiniciar.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}