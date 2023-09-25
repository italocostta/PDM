package com.example.acerteonumero

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText


class MainActivity : AppCompatActivity() {

    private lateinit var jogo: Jogo
    private lateinit var tituloTextView: TextView
    private lateinit var valorMinTextView: TextView
    private lateinit var valorMaxTextView: TextView
    private lateinit var chuteTextInput: TextInputEditText
    private lateinit var jogoStatus: TextView
    private lateinit var botaoChutar: Button
    private lateinit var nomeJogadorTextView: TextView

    fun atualizarInterface() {
        valorMinTextView.text = jogo.getMenor().toString()
        valorMaxTextView.text = jogo.getMaior().toString()
        chuteTextInput.text?.clear()

        when (jogo.getStatus()) {
            Status.VITORIA -> {
                tituloTextView.text = "O jogo terminou. Inicie um novo jogo."
                val intent = Intent(this, TelaVenceu::class.java)
                intent.putExtra("nomeJogador", "Nome do Jogador") // Substitua pelo nome real do jogador
                startActivity(intent)
            }
            Status.DERROTA -> {
                tituloTextView.text = "Você PERDEU!!!"
                val intent = Intent(this, TelaPerdeu::class.java)
                startActivity(intent)
            }
            Status.EM_ANDAMENTO -> {
                tituloTextView.text = "Tente novamente. O número está entre ${jogo.getMenor()} e ${jogo.getMaior()}"
            }
        }
    }

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

        val nomeJogador = intent.getStringExtra("NOME_JOGADOR")
        if (!nomeJogador.isNullOrEmpty()) {
            nomeJogadorTextView.text = "Ultimo vencedor: $nomeJogador"
        }


        valorMinTextView.text = jogo.getMenor().toString()
        valorMaxTextView.text = jogo.getMaior().toString()

        botaoChutar.setOnClickListener(clicaChute)
        jogoStatus.setOnLongClickListener(clicaReinicia)
    }

    val clicaReinicia = View.OnLongClickListener {
        jogo = Jogo()
        atualizarInterface()

        Toast.makeText(this@MainActivity, "Jogo reiniciado", Toast.LENGTH_SHORT).show()

        true
    }

    val clicaChute = View.OnClickListener {
        val chuteTexto = chuteTextInput.text.toString()
        val chuteNumerico = chuteTexto.toIntOrNull()

        if (chuteNumerico == null) {
            chuteTextInput.error = "Digite um número válido"
            return@OnClickListener
        }

        jogo.chutar(chuteNumerico)
        atualizarInterface()
    }

}





