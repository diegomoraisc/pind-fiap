package br.com.example.pind

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class EstoqueActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_estoque)

        val recyclerView = findViewById<RecyclerView>(R.id.rv_estoque)
        val btnHome = findViewById<ImageView>(R.id.iv_nav_home)
        val btnLogout = findViewById<ImageView>(R.id.iv_nav_logout)
        val btnAdd = findViewById<FloatingActionButton>(R.id.add_btn)
        val btnRemoveEstoque = findViewById<FloatingActionButton>(R.id.remove_btn)
        val estoqueDialog = EstoqueDialog()
        val estoqueList = mutableListOf(
            EstoqueListItem("Exemplo", "0kg"),
        )
        val estoqueAdapter = EstoqueListAdapter(estoqueList)

        recyclerView.adapter = estoqueAdapter

        btnHome.setOnClickListener {
            val i = Intent(this, HomeFragment::class.java)
            startActivity(i)
        }

        btnLogout.setOnClickListener {
            val i = Intent(this, LoginActivity::class.java)
            startActivity(i)
        }

        btnAdd.setOnClickListener {
            estoqueDialog.show(supportFragmentManager, "dialogAdd")
        }

        estoqueDialog.onAddItem = {
            estoqueList.add(it)
            estoqueAdapter.notifyItemInserted(estoqueList.indexOf(it))
        }

        btnRemoveEstoque.setOnClickListener {
            estoqueAdapter.onRemoveEnable()
            estoqueAdapter.notifyItemRangeChanged(
                estoqueList.indexOfFirst { true },
                estoqueList.size
            )
        }

        estoqueAdapter.onItemRemoved = {
            val index = estoqueList.indexOf(it)
            estoqueList.remove(it)
            estoqueAdapter.notifyItemRemoved(index)
        }
    }
}