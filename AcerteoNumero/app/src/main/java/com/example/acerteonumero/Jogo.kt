package com.example.acerteonumero

import kotlin.random.Random

enum class Status {
    VITORIA,
    DERROTA,
    EM_ANDAMENTO
}

class Jogo {
    private var valorSorteado = Random.nextInt(1, 101)
    private var menor = 1
    private var maior = 100
    private var status = Status.EM_ANDAMENTO

    fun chutar(numero: Int) {
        when {
            numero == valorSorteado -> {
                status = Status.VITORIA
            }
            numero !in menor..maior -> {
                status = Status.DERROTA
            }
            menor == maior -> {
                status = Status.DERROTA
            }
            numero < valorSorteado -> {
                menor = numero + 1
            }
            numero > valorSorteado -> {
                maior = numero - 1
            }

            else -> maior = numero - 1
        }
    }


    fun getStatus(): Status {
        return status
    }

    fun getMaior(): Int {
        return maior
    }

    fun getMenor(): Int {
        return menor
    }

}