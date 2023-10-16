package com.example.passgenerator


import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var addBtn: Button
    private lateinit var passwordNames: MutableList<String>
    private lateinit var adapter: ArrayAdapter<String>
    private val ADD_PASSWORD_REQUEST = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.password_listview)
        addBtn = findViewById(R.id.add_button)

        passwordNames = mutableListOf()



        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, passwordNames)
        listView.adapter = adapter

        listView.setOnItemClickListener { parent, view, position, id ->
            val selectedPasswordName = passwordNames[position]
            val intent = Intent(this, DisplayPasswordActivity::class.java)
            intent.putExtra("passwordDescription", selectedPasswordName)
            // Substitua os valores a seguir pelos valores reais
            intent.putExtra("passwordLength", 8) // exemplo de valor de tamanho
            intent.putExtra("includeCapitalLetters", true) // exemplo de valor de letras maiúsculas
            intent.putExtra("includeNumbers", true) // exemplo de valor de números
            intent.putExtra("includeSpecialCharacters", true) // exemplo de valor de caracteres especiais
            startActivity(intent)
        }

        addBtn.setOnClickListener {
            val intent = Intent(this, CreatePasswordActivity::class.java)
            startActivityForResult(intent, ADD_PASSWORD_REQUEST)
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ADD_PASSWORD_REQUEST && resultCode == RESULT_OK) {
            val addedPasswordName = data?.getStringExtra("addedPasswordName")
            if (addedPasswordName != null && !passwordNames.contains(addedPasswordName)) {
                passwordNames.add(addedPasswordName)
                adapter.notifyDataSetChanged()
            }

        } else if (requestCode == PASSWORD_DETAILS_REQUEST && resultCode == RESULT_OK) {
            if (data?.getBooleanExtra("isDeleted", false) == true) {
                val deletedPasswordName = data.getStringExtra("selectedPasswordName")
                passwordNames.remove(deletedPasswordName)
                adapter.notifyDataSetChanged()
            }
        }
    }

    companion object {
        const val PASSWORD_DETAILS_REQUEST = 2 // valor de requisição para a atividade DisplayPasswordActivity
    }

    private fun getPasswordFromPosition(position: Int): String {
        return passwordNames[position]
    }

}
