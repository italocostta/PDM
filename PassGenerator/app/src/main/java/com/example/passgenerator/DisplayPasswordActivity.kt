package com.example.passgenerator

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent

class DisplayPasswordActivity : AppCompatActivity() {

    private lateinit var descriptionEditText: EditText
    private lateinit var lengthSeekBar: SeekBar
    private lateinit var capitalLettersCheckbox: CheckBox
    private lateinit var numbersCheckbox: CheckBox
    private lateinit var specialCharactersCheckbox: CheckBox
    private lateinit var updateButton: Button
    private lateinit var deleteButton: Button
    private lateinit var cancelButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_password)

        descriptionEditText = findViewById(R.id.description_edittext)
        lengthSeekBar = findViewById(R.id.length_seekbar)
        capitalLettersCheckbox = findViewById(R.id.capital_letters_checkbox)
        numbersCheckbox = findViewById(R.id.numbers_checkbox)
        specialCharactersCheckbox = findViewById(R.id.special_characters_checkbox)
        updateButton = findViewById(R.id.update_button)
        deleteButton = findViewById(R.id.delete_button)
        cancelButton = findViewById(R.id.cancel_button)

        // Recupere os valores das configurações atuais
        val currentDescription = intent.getStringExtra("passwordDescription")
        val currentLength = intent.getIntExtra("passwordLength", 8)
        val currentIncludeCapitalLetters = intent.getBooleanExtra("includeCapitalLetters", true)
        val currentIncludeNumbers = intent.getBooleanExtra("includeNumbers", true)
        val currentIncludeSpecialCharacters =
            intent.getBooleanExtra("includeSpecialCharacters", true)

        // Preencha os elementos da UI com os valores atuais
        descriptionEditText.setText(currentDescription)
        lengthSeekBar.progress = currentLength - 1
        capitalLettersCheckbox.isChecked = currentIncludeCapitalLetters
        numbersCheckbox.isChecked = currentIncludeNumbers
        specialCharactersCheckbox.isChecked = currentIncludeSpecialCharacters

        updateButton.setOnClickListener {

            finish()
        }

        deleteButton.setOnClickListener {

            finish()
        }

        cancelButton.setOnClickListener {

            finish()
        }
    }
}