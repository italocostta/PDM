package com.example.acerteonumero

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText


class MainActivity : AppCompatActivity() {

    private lateinit var jogo: Jogo
    private lateinit var tituloTextView: TextView
    private lateinit var valorMinTextView: TextView
    private lateinit var valorMaxTextView: TextView
    private lateinit var chuteTextInput: TextInputEditText
    private lateinit var jogoStatus: TextView
    private lateinit var botaoChutar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tituloTextView = findViewById(R.id.titulo)
        valorMinTextView = findViewById(R.id.valorMin)
        valorMaxTextView = findViewById(R.id.valorMax)
        chuteTextInput = findViewById(R.id.chuteTextInput)
        botaoChutar = findViewById(R.id.botaoChutar)
        jogoStatus = findViewById(R.id.jogoStatus)

        jogo = Jogo()

        valorMinTextView.text = jogo.getMenor().toString()
        valorMaxTextView.text = jogo.getMaior().toString()

        fun atualizarInterface(resultado: String) {
            valorMinTextView.text = jogo.getMenor().toString()
            valorMaxTextView.text = jogo.getMaior().toString()
            chuteTextInput.text?.clear()
            tituloTextView.text = resultado

            when (jogo.getStatus()) {
                Jogo.Status.VITORIA.toString() -> {
                    val intent = Intent(this, TelaVenceu::class.java)
                    intent.putExtra("nomeJogador", "Nome do Jogador") // Substitua pelo nome real do jogador
                    startActivity(intent)
                }
                Jogo.Status.DERROTA.toString() -> {
                    val intent = Intent(this, TelaPerdeu::class.java)
                    startActivity(intent)
                }
            }
        }


        val nomeVencedor = intent.getStringExtra("nomeVencedor")
        if (nomeVencedor != null && nomeVencedor.isNotEmpty()) {
            tituloTextView.text = "Parabéns, $nomeVencedor, você venceu!"
        }


        botaoChutar.setOnClickListener {
            val chuteTexto = chuteTextInput.text.toString()
            val chuteNumerico = chuteTexto.toIntOrNull()

            if (chuteNumerico != null) {
                val resultado = jogo.chutar(chuteNumerico)
                atualizarInterface(resultado)
            } else {
                chuteTextInput.error = "Digite um número válido"
            }
        }

        jogoStatus.setOnLongClickListener(View.OnLongClickListener {
            jogo.iniciaJogo()
            atualizarInterface("Acerte o número!")


            Toast.makeText(this@MainActivity, "Jogo reiniciado", Toast.LENGTH_SHORT).show()

            true
        })


        val nomeJogador = intent.getStringExtra("NOME_JOGADOR")
        if (nomeJogador != null && nomeJogador.isNotEmpty()) {

            val nomeJogadorTextView = findViewById<TextView>(R.id.nomeJogadorTextView)

            nomeJogadorTextView.text = "Ultimo vencedor: $nomeJogador"
        }
    }
}





