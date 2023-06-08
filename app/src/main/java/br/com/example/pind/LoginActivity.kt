package br.com.example.pind

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import br.com.example.pind.api.caller.AuthCaller
import br.com.example.pind.api.models.AuthModel
import br.com.example.pind.api.models.LoginResponse
import br.com.example.pind.api.services.Token
import com.google.android.material.textfield.TextInputLayout
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)



        val btnLogin = findViewById<Button>(R.id.btn_login)
        val btnCadastro = findViewById<TextView>(R.id.btn_cadastro)

        btnLogin.setOnClickListener {
            val textEmail: TextInputLayout = findViewById(R.id.email_input)
            val textPass: TextInputLayout = findViewById(R.id.password_input)

            val request = AuthModel(textEmail.toString(), textPass.toString())
            val call = AuthCaller().authService().athenticate(request)

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
                        if(loginResponse?.token != "" && loginResponse != null){
                            Token.staticToken = loginResponse.token
                            Log.i("token", Token.staticToken)
                        }
                    }
                }
                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })

            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }

        btnCadastro.setOnClickListener {
            val i = Intent(this, CadastroActivity::class.java)
            startActivity(i)
        }
    }
}