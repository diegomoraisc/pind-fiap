package br.com.example.pind.screens.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import br.com.example.pind.screens.MainActivity
import br.com.example.pind.R
import br.com.example.pind.screens.cadastro.CadastroActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val btnLogin = findViewById<Button>(R.id.btn_login)
        val btnCadastro = findViewById<TextView>(R.id.btn_cadastro)

        btnLogin.setOnClickListener {
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }

        btnCadastro.setOnClickListener {
            val i = Intent(this, CadastroActivity::class.java)
            startActivity(i)
        }
    }
}