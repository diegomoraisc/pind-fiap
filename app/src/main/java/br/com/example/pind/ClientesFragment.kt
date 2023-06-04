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

    private var btnAdd: Button? = null
    private var btnRemove: Button? = null
    private var btnCancel: Button? = null

    private var clientesDialog: ClientesDialog? = null
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

        btnAdd = view.findViewById(R.id.btn_add_clientes)
        btnRemove = view.findViewById(R.id.btn_remove_clientes)
        btnCancel = view.findViewById(R.id.btn_cancel_clientes)

        //TODO adicionar os dados mockados do cliente após alterar o Objeto "Cliente.kt"
        val clientesList = mutableListOf(
            Cliente(0, "Endrew", "11/09/2022", "18/02/2023"),
            Cliente(0, "Diego", "11/09/2022", "18/02/2023"),
            Cliente(0, "Roberval", "11/09/2022", "18/02/2023"),
            Cliente(0, "Zé Da Manga", "11/09/2022", "18/02/2023"),
            Cliente(0, "Aristovaldo", "11/09/2022", "18/02/2023")
        )

        val clientesAdapter = ClientesListAdapter(clientesList)
        recyclerView.adapter = clientesAdapter

        clientesAdapter.onCheckBoxEnabled = {
            clientesAdapter.notifyItemRangeChanged(0, clientesList.size)
            setButtonVisibility(true)
        }
        clientesAdapter.onItemClicked = {
            clientesDialog = ClientesDialog.newInstance(it)
            clientesDialog?.show(requireActivity().supportFragmentManager, "clienteDialog")
        }
        clientesAdapter.onClickFinalizados = {
            Toast.makeText(requireContext(), "Finalizados", Toast.LENGTH_SHORT).show()
        }
        clientesAdapter.onClickEmAndamento = {
            Toast.makeText(requireContext(), "Em Andamento", Toast.LENGTH_SHORT).show()
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
                clientesAdapter.notifyItemRemoved(position)
            }
        }
        btnAdd?.setOnClickListener {
            clientesDialog = ClientesDialog.newInstance()
            clientesDialog?.show(requireActivity().supportFragmentManager, "clienteDialog")
        }

        //TODO Adicionar informações do cliente no banco
        /*clientesDialog.onAddItem = {
            clientesList.add(it)
            clientesAdapter.notifyItemInserted(clientesList.indexOf(it))
        }*/
    }

    private fun setButtonVisibility (isVisible: Boolean){
        btnRemove?.isVisible = isVisible
        btnCancel?.isVisible = isVisible
        btnAdd?.isVisible = !isVisible
    }
}

