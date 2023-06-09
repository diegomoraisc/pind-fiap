package br.com.example.pind.screens.cadastro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import br.com.example.pind.R
import br.com.example.pind.api.caller.PreferencesHelper
import br.com.example.pind.api.caller.ProductsCaller
import br.com.example.pind.api.models.LoginResponse
import br.com.example.pind.api.models.RegisterModel
import br.com.example.pind.modal.cliente.services.AuthService
import br.com.example.pind.screens.MainActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CadastroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        val preferenceHelper = PreferencesHelper(this@CadastroActivity)

        val btnSign = findViewById<Button>(R.id.btn_signin)
        btnSign.setOnClickListener {
            val textName : EditText = findViewById(R.id.nome_input_text)
            val textEmail: EditText = findViewById(R.id.email_input_text)
            val textPass: EditText = findViewById(R.id.password_input_text)

            val request = RegisterModel(textEmail.text.toString(), textPass.text.toString(), textName.text.toString())
            val call = ProductsCaller(this).productsService().create(AuthService :: class.java).register(request)

            call.enqueue(object: Callback<LoginResponse> {
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
                            val i = Intent(this@CadastroActivity, MainActivity::class.java)
                            startActivity(i)
                        }

                    }
                }
                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Log.e("err", t.message.toString())
                }

            })


        }
    }
}