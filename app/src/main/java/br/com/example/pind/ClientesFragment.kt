package br.com.example.pind

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView

class ClientesFragment : Fragment() {
    var btnAdd: Button? = null
    var btnRemove: Button? = null
    var btnCancel: Button? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_clientes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_clientes)
        val clientesDialog = ClientesDialog()
        btnAdd = view.findViewById(R.id.btn_add_clientes)
        btnRemove = view.findViewById(R.id.btn_remove_clientes)
        btnCancel = view.findViewById(R.id.btn_cancel_clientes)
        val clientesList = mutableListOf(
            ClientesListItem(0, "Endrew", "11/09/2022", "18/02/2023"),
            ClientesListItem(0, "Endrew", "11/09/2022", "18/02/2023"),
            ClientesListItem(0, "Endrew", "11/09/2022", "18/02/2023"),
            ClientesListItem(0, "Endrew", "11/09/2022", "18/02/2023"),
            ClientesListItem(0, "Endrew", "11/09/2022", "18/02/2023")
        )
        val clientesAdapter = ClientesListAdapter(clientesList)
        recyclerView.adapter = clientesAdapter
        clientesAdapter.onCheckBoxEnabled = {
            clientesAdapter.notifyItemRangeChanged(0, clientesList.size)
            setButtonVisibility(true)
        }

        clientesAdapter.onClickFinalizados = {
            Toast.makeText(requireContext(), it.nomeCliente, Toast.LENGTH_SHORT).show()
        }
        clientesAdapter.onClickEmAndamento = {
            Toast.makeText(requireContext(), it.nomeCliente, Toast.LENGTH_SHORT).show()
        }
        btnCancel?.setOnClickListener {
            clientesAdapter.isEnabled = false
            clientesAdapter.clearCheckBox = true
            clientesAdapter.selectedList.clear()
            clientesAdapter.notifyItemRangeChanged(0, clientesList.size)
            setButtonVisibility(false)
        }
        btnRemove?.setOnClickListener {
            clientesAdapter.isEnabled = false
            clientesAdapter.clearCheckBox = true
            clientesAdapter.selectedList.forEach{ item ->
                val position = clientesList.indexOf(item)
                clientesList.removeAt(position)
            }
            clientesAdapter.notifyItemRangeRemoved(0, clientesList.size)
        }
        btnAdd?.setOnClickListener {
            clientesDialog.show(requireActivity().supportFragmentManager, "clienteDialog")
        }

        //TODO Adicionar informações do cliente no banco
        /*clientesDialog.onAddItem = {
            clientesList.add(it)
            clientesAdapter.notifyItemInserted(clientesList.indexOf(it))
        }*/
    }

    fun setButtonVisibility (isVisible: Boolean){
        btnRemove?.isVisible = isVisible
        btnCancel?.isVisible = isVisible
        btnAdd?.isVisible = !isVisible
    }
}

