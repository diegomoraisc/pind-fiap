package br.com.example.pind.screens

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.findNavController
import br.com.example.pind.R
import br.com.example.pind.screens.login.LoginActivity

class MainActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHost = findViewById<FragmentContainerView>(R.id.fcv_nav_host_fragment)
        val btnHome = findViewById<ImageView>(R.id.iv_nav_home)
        val btnLogout = findViewById<ImageView>(R.id.iv_nav_logout)

        btnHome.setOnClickListener {
            navHost.findNavController().navigate(R.id.homeFragment)
        }

        btnLogout.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}