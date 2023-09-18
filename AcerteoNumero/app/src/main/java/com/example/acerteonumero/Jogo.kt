package com.example.acerteonumero

import kotlin.random.Random

class Jogo {
    private var valorSorteado: Int = Random.nextInt(1, 101)
    private var menor: Int = 1
    private var maior: Int = 100
    private var status: Status = Status.EM_ANDAMENTO

    enum class Status {
        VITORIA,
        DERROTA,
        EM_ANDAMENTO
    }

    fun chutar(numero: Int): String {
        if (status != Status.EM_ANDAMENTO) {
            return "O jogo terminou. Inicie um novo jogo."
        }
        if (numero == valorSorteado) {
            status = Status.VITORIA
            return status.name
        } else if (numero < menor || numero > maior) {
            status = Status.DERROTA
            return "Você PERDEU!!!"
        } else if (numero < valorSorteado) {
            menor = numero + 1
        } else if (numero > valorSorteado) {
            maior = numero - 1
        }
        if (menor == maior) {
            status = Status.DERROTA
            return "Você PERDEU!!!"
        }
        return "Tente novamente. O número está entre $menor e $maior"    }


        fun iniciaJogo() {
            valorSorteado = Random.nextInt(1, 101)
            menor = 1
            maior = 100
            status = Status.EM_ANDAMENTO
        }

        fun getStatus(): String {
            return status.name
        }

        fun getMaior(): Int {
            return maior
        }

        fun getMenor(): Int {
            return menor
        }

}