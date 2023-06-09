package br.com.example.pind

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import br.com.example.pind.api.caller.ProductsCaller
import br.com.example.pind.api.models.ProductModel
import br.com.example.pind.api.services.ProductsService
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EstoqueActivity : AppCompatActivity() {


    var estoqueAdapter : EstoqueListAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_estoque)
        getProduct()

        val btnHome = findViewById<ImageView>(R.id.iv_nav_home)
        val btnLogout = findViewById<ImageView>(R.id.iv_nav_logout)
        val btnAdd = findViewById<FloatingActionButton>(R.id.add_btn)
        val btnRemoveEstoque = findViewById<FloatingActionButton>(R.id.remove_btn)
        val estoqueDialog = EstoqueDialog()





        btnHome.setOnClickListener {
           finish()
        }

        btnLogout.setOnClickListener {
            val i = Intent(this, LoginActivity::class.java)
            startActivity(i)
        }

        btnAdd.setOnClickListener {
            estoqueDialog.show(supportFragmentManager, "dialogAdd")
        }

        estoqueDialog.onAddItem = {
            getProduct()
        }

        btnRemoveEstoque.setOnClickListener {

        }

    }

    fun getProduct(){
        val recyclerView = findViewById<RecyclerView>(R.id.rv_estoque)
        val call = ProductsCaller(this)
            .productsService()
            .create(ProductsService :: class.java)
            .getProduct()

        call.enqueue(object : Callback<List<ProductModel>>{
            override fun onResponse(
                call: Call<List<ProductModel>>,
                response: Response<List<ProductModel>>
            ) {
                if(response.isSuccessful){
                    estoqueAdapter = response.body()?.let { EstoqueListAdapter(it) }
                    recyclerView.adapter = estoqueAdapter
                }
            }

            override fun onFailure(call: Call<List<ProductModel>>, t: Throwable) {

            }

        })
    }
}