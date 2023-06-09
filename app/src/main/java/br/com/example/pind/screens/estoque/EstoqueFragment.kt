package br.com.example.pind.screens.estoque

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import br.com.example.pind.R
import br.com.example.pind.api.caller.ProductsCaller
import br.com.example.pind.api.models.ProductModel
import br.com.example.pind.modal.cliente.services.ProductsService
import br.com.example.pind.screens.estoque.adapter.EstoqueListAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EstoqueFragment : Fragment() {

    private var estoqueAdapter: EstoqueListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_estoque, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_estoque)
        val btnAdd = view.findViewById<FloatingActionButton>(R.id.add_btn)
        val btnRemoveEstoque = view.findViewById<FloatingActionButton>(R.id.remove_btn)
        val estoqueDialog = EstoqueDialog()

        getProduct(recyclerView)

        btnAdd.setOnClickListener {
            estoqueDialog.show(parentFragmentManager, "dialogAdd")
        }

        estoqueDialog.onAddItem = {
            getProduct(recyclerView)
        }

        btnRemoveEstoque.setOnClickListener {

        }

    }

    private fun getProduct(recyclerView: RecyclerView) {
        val call = ProductsCaller(requireContext())
            .productsService()
            .create(ProductsService::class.java)
            .getProduct()

        call.enqueue(object : Callback<List<ProductModel>> {
            override fun onResponse(
                call: Call<List<ProductModel>>,
                response: Response<List<ProductModel>>
            ) {
                if (response.isSuccessful) {
                    estoqueAdapter = response.body()?.let { EstoqueListAdapter(it) }
                    recyclerView.adapter = estoqueAdapter
                }
            }

            override fun onFailure(call: Call<List<ProductModel>>, t: Throwable) {}

        })
    }
}