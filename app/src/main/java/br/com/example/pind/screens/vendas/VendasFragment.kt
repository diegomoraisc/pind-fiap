package br.com.example.pind.screens.vendas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import br.com.example.pind.R
import br.com.example.pind.modal.pedido.Pedido
import br.com.example.pind.screens.pedidos.adapter.VendasAdapter

class VendasFragment:Fragment() {

    private var btnCancel: Button? = null
    private var btnAdd: Button? = null
    private var btnRemove: Button? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_vendas, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_vendas)
        btnRemove = view.findViewById<Button>(R.id.btn_remove_vendas)
        btnAdd = view.findViewById<Button>(R.id.btn_add_vendas)
        btnCancel = view.findViewById<Button>(R.id.btn_cancel)

        val vendasDialog = VendasDialog.newInstance()

        // lista mockada
        val vendasList = mutableListOf(
            Pedido(0,"14 maio 2023", "Cliente X", "Pão", "5000", 7000.00),
            Pedido(1,"14 maio 2023", "Cliente Y", "Alho", "6000", 8000.00),
            Pedido(2,"14 maio 2023", "Cliente Zé", "Manga", "7000", 9000.00),
            Pedido(3,"14 maio 2023", "Cliente A", "Cebola", "8000", 10000.00),
        )
        val vendasAdapter = VendasAdapter(vendasList)
        recyclerView.adapter = vendasAdapter

        vendasAdapter.onCheckBoxEnabled = {
            vendasAdapter.notifyItemRangeChanged(0, vendasList.size)
            setButtonVisibility(true)
        }

        btnAdd?.setOnClickListener {
            vendasDialog.show(childFragmentManager, "vendasDialog")
        }

        btnCancel?.setOnClickListener {
            vendasAdapter.isEnabled = false
            vendasAdapter.clearCheckBox = true
            vendasAdapter.selectedList.clear()
            vendasAdapter.notifyItemRangeChanged(0, vendasList.size)
            setButtonVisibility(false)
        }
        btnRemove?.setOnClickListener {
            vendasAdapter.isEnabled = false
            vendasAdapter.clearCheckBox = true
            vendasAdapter.selectedList.forEach{ item ->
                val position = vendasList.indexOf(item)
                vendasList.removeAt(position)
                vendasAdapter.notifyItemRemoved(position)
            }
        }

        vendasDialog.onAddItem = {
            //TODO Mandar para API
            Toast.makeText(requireContext(), "${ it.produto } Adicionado(a) ", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setButtonVisibility (isVisible: Boolean){
        btnRemove?.isVisible = isVisible
        btnCancel?.isVisible = isVisible
        btnAdd?.isVisible = !isVisible
    }
}