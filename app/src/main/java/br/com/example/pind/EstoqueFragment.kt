package br.com.example.pind

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class EstoqueFragment : Fragment() {

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
        val btnAdd =  view.findViewById<FloatingActionButton>(R.id.add_btn)
        val btnRemoveEstoque =  view.findViewById<FloatingActionButton>(R.id.remove_btn)
        val estoqueDialog = EstoqueDialog()
        val estoqueList = mutableListOf(
            EstoqueListItem("Exemplo", "0kg"),
        )
        val estoqueAdapter = EstoqueListAdapter(estoqueList)

        recyclerView.adapter = estoqueAdapter

        btnAdd.setOnClickListener {
            estoqueDialog.show(requireActivity().supportFragmentManager, "dialogAdd")
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