package br.com.example.pind

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import br.com.example.pind.api.caller.PreferencesHelper
import br.com.example.pind.api.caller.ProductsCaller
import br.com.example.pind.api.models.AuthModel
import br.com.example.pind.api.models.LoginResponse
import br.com.example.pind.api.services.AuthService
import br.com.example.pind.api.services.Token
import retrofit2.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val preferenceHelper = PreferencesHelper(this@LoginActivity)
        if (preferenceHelper.getRefreshToken() != null){
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }

        val btnLogin = findViewById<Button>(R.id.btn_login)
        val btnCadastro = findViewById<TextView>(R.id.btn_cadastro)

        btnLogin.setOnClickListener {
            val textEmail: EditText = findViewById(R.id.email_input_text)
            val textPass: EditText = findViewById(R.id.password_input_text)

            val request = AuthModel(textEmail.text.toString(), textPass.text.toString())
            val call = ProductsCaller(this).productsService().create(AuthService :: class.java).athenticate(request)

            call.enqueue(object: Callback<LoginResponse>{
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    if(response.isSuccessful){
                        val loginResponse = response.body()
                        if(loginResponse == null){
                            Toast.makeText(applicationContext, "erro no servidor", Toast.LENGTH_SHORT).show()
                        }
                        if(loginResponse?.refreshToken != "" && loginResponse != null){
                            preferenceHelper.setRefreshToken(loginResponse.refreshToken)
                            val i = Intent(this@LoginActivity, MainActivity::class.java)
                            startActivity(i)
                        }

                    }
                }
                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Log.e("err", t.message.toString())
                }

            })


        }

        btnCadastro.setOnClickListener {
            val i = Intent(this, CadastroActivity::class.java)
            startActivity(i)
        }
    }
}