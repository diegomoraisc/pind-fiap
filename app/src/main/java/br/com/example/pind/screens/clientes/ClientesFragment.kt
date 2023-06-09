package br.com.example.pind.screens.clientes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import br.com.example.pind.screens.clientes.adapter.ClientesListAdapter
import br.com.example.pind.R
import br.com.example.pind.modal.cliente.PedidoCliente

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

        val clientesList = mutableListOf(
            PedidoCliente(
                0,
                "Diego",
                "212312313112313",
                "Diego@email.com",
                "11 98888-8888",
                "06666-666",
                "Rua Fim dos tempos",
                "Apocalipse",
                "Cotia",
                "SP",
                "11/09/2022",
                "18/02/2023"
            ),
            PedidoCliente(
                0,
                "Diego",
                "212312313112313",
                "Diego@email.com",
                "11 98888-8888",
                "06666-666",
                "Rua Fim dos tempos",
                "Apocalipse",
                "Cotia",
                "SP",
                "11/09/2022",
                "18/02/2023"
            ),
            PedidoCliente(
                0,
                "Diego",
                "212312313112313",
                "Diego@email.com",
                "11 98888-8888",
                "06666-666",
                "Rua Fim dos tempos",
                "Apocalipse",
                "Cotia",
                "SP",
                "11/09/2022",
                "18/02/2023"
            ),
            PedidoCliente(
                0,
                "Diego",
                "212312313112313",
                "Diego@email.com",
                "11 98888-8888",
                "06666-666",
                "Rua Fim dos tempos",
                "Apocalipse",
                "Cotia",
                "SP",
                "11/09/2022",
                "18/02/2023"
            ),
            PedidoCliente(
                0,
                "Diego",
                "212312313112313",
                "Diego@email.com",
                "11 98888-8888",
                "06666-666",
                "Rua Fim dos tempos",
                "Apocalipse",
                "Cotia",
                "SP",
                "11/09/2022",
                "18/02/2023"
            )
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
        clientesAdapter.onClickFinalizados = { pedido ->
            // Passar o ID do cliente para fazer a request na tela de pedidos finalizados.
            pedido.id?.let { it ->
                findNavController().navigate(ClientesFragmentDirections.actionClientesFragmentToPedidosFinalizadosFragment(
                    it
                ))
            }
        }
        clientesAdapter.onClickEmAndamento = { pedido ->
            // Passar o ID do cliente para fazer a request na tela de pedidos finalizados.
            pedido.id?.let { it ->
                findNavController().navigate(ClientesFragmentDirections.actionClientesFragmentToPedidosAndamentoFragment(
                    it
                ))
            }
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
            clientesAdapter.selectedList.forEach { item ->
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
        clientesDialog?.onAddItem = { cliente ->
            Toast.makeText(requireContext(), "Cliente adicionado.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setButtonVisibility(isVisible: Boolean) {
        btnRemove?.isVisible = isVisible
        btnCancel?.isVisible = isVisible
        btnAdd?.isVisible = !isVisible
    }
}

