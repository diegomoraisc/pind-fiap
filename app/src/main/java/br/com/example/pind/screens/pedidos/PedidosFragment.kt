package br.com.example.pind.screens.pedidos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import br.com.example.pind.R
import br.com.example.pind.modal.pedido.Pedido
import br.com.example.pind.modal.pedido.PedidosCliente
import br.com.example.pind.screens.pedidos.adapter.PedidosAdapter
import br.com.example.pind.screens.pedidos.adapter.VendasAdapter

class PedidosFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_pedidos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_pedidos)

        // lista mockada
        val pedidos = mutableListOf(
            PedidosCliente("Diego", 45, 15, 30),
            PedidosCliente("Diego", 20, 10, 10),
            PedidosCliente("Diego", 100, 50, 50),
            PedidosCliente("Diego", 200, 150, 50),
        )

        val adapter = PedidosAdapter(pedidos)
        recyclerView.adapter = adapter
    }
}