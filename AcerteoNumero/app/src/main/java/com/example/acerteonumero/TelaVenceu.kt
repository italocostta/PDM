package com.example.acerteonumero

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text

class TelaVenceu : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_venceu)

        val editTextNomeJogador = findViewById<EditText>(R.id.editTextNomeJogador)

        val mensagemVitoria = "Parabéns, você venceu!"
        val mensagemTextView = findViewById<TextView>(R.id.mensagemVitoria)
        mensagemTextView.text = mensagemVitoria

        val botaoReiniciar = findViewById<Button>(R.id.botaoReiniciar)
        botaoReiniciar.setOnClickListener {
            val nomeJogador = editTextNomeJogador.text.toString()
            val intent = Intent(this@TelaVenceu, MainActivity::class.java)
            intent.putExtra("NOME_JOGADOR", nomeJogador)
            startActivity(intent)
            finish()
        }
    }
}
