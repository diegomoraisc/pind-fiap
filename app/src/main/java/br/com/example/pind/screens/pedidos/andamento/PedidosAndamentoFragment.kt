package br.com.example.pind.screens.pedidos.andamento

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import br.com.example.pind.R
import br.com.example.pind.screens.pedidos.adapter.VendasAdapter
import br.com.example.pind.modal.pedido.Pedido
import br.com.example.pind.screens.pedidos.finalizados.PedidosFinalizadosFragmentArgs

class PedidosAndamentoFragment: Fragment() {

    private val navArgs: PedidosFinalizadosFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pedidos_andamento, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /** adicionar chamada para API, para buscar a lista de pedidos em andamentos. (usar ID do cliente passado atravéz do "navArgs").
          * Exemplo:
         *  @sample navArgs.clienteId
         **/

        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_pedidos_andamentos)
        val btnConcluir = view.findViewById<Button>(R.id.btn_concluir_pedidos)

        // lista mockada
        val pedidos = mutableListOf(
            Pedido(0,"14/06/2023", "Cliente X", "Pão", "5000", 7000.00),
            Pedido(1,"14/06/2023", "Cliente Y", "Alho", "6000", 8000.00),
            Pedido(2,"14/06/2023", "Cliente Zé", "Manga", "7000", 9000.00),
            Pedido(3,"14/06/2023", "Cliente A", "Cebola", "8000", 10000.00),
        )

        val adapter = VendasAdapter(pedidos)
        recyclerView.adapter = adapter

        adapter.onCheckBoxEnabled = {
            adapter.notifyItemRangeChanged(0, pedidos.size)
        }

        btnConcluir?.setOnClickListener {
            adapter.isEnabled = false
            adapter.clearCheckBox = true

            Toast.makeText(requireContext(), "${adapter.selectedList.size} Pedidos concluidos", Toast.LENGTH_SHORT).show()
            // TODO mandar para API a lista de pedidos concluidos -> adapter.selectedList
        }

    }
}