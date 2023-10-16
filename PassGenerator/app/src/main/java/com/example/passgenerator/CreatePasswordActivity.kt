package com.example.passgenerator

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CreatePasswordActivity : AppCompatActivity() {

    private lateinit var generateButton: Button
    private lateinit var cancelButton: Button
    private lateinit var descriptionEditText: EditText
    private lateinit var lengthSeekBar: SeekBar
    private lateinit var capitalLettersCheckbox: CheckBox
    private lateinit var numbersCheckbox: CheckBox
    private lateinit var specialCharactersCheckbox: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_password_activity)

        generateButton = findViewById(R.id.generate_button)
        cancelButton = findViewById(R.id.cancel_button)
        descriptionEditText = findViewById(R.id.description_edittext)
        lengthSeekBar = findViewById(R.id.length_seekbar)
        capitalLettersCheckbox = findViewById(R.id.capital_letters_checkbox)
        numbersCheckbox = findViewById(R.id.numbers_checkbox)
        specialCharactersCheckbox = findViewById(R.id.special_characters_checkbox)

        generateButton.setOnClickListener {
            val length = lengthSeekBar.progress + 1
            val includeCapitalLetters = capitalLettersCheckbox.isChecked
            val includeNumbers = numbersCheckbox.isChecked
            val includeSpecialCharacters = specialCharactersCheckbox.isChecked
            val description = descriptionEditText.text.toString()

            val generatedPassword = generatePassword(length, includeCapitalLetters, includeNumbers, includeSpecialCharacters)
            Toast.makeText(this, "Descrição: $description", Toast.LENGTH_SHORT).show()

            val resultIntent = Intent()
            resultIntent.putExtra("addedPasswordName", description)
            setResult(RESULT_OK, resultIntent)

            finish()
        }


        cancelButton.setOnClickListener {
            finish()
        }
    }

    private fun generatePassword(
        length: Int,
        includeCapitalLetters: Boolean,
        includeNumbers: Boolean,
        includeSpecialCharacters: Boolean
    ): String {
        val charPool = mutableListOf<Char>()
        charPool.addAll('a'..'z')
        if (includeCapitalLetters) charPool.addAll('A'..'Z')
        if (includeNumbers) charPool.addAll('0'..'9')
        if (includeSpecialCharacters) charPool.addAll(listOf('!', '@', '#', '$', '%', '^', '&', '*', '(', ')'))

        var password = ""
        // Adiciona pelo menos um caractere de cada tipo
        if (includeCapitalLetters) password += charPool.random()
        if (includeNumbers) password += charPool.random()
        if (includeSpecialCharacters) password += charPool.random()

        // Adiciona caracteres restantes para atingir o comprimento desejado
        repeat(length - password.length) {
            password += charPool.random()
        }

        return password.toList().shuffled().joinToString("")
    }

}
