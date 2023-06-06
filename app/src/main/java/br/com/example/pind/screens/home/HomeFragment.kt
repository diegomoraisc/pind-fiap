package br.com.example.pind.screens.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import br.com.example.pind.screens.home.adapter.HomePageMenuAdapter
import br.com.example.pind.R
import br.com.example.pind.modal.menu.HomeMenuItem

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recycleView = view.findViewById<RecyclerView>(R.id.rv_menu)

        val menuCardList = listOf(
            HomeMenuItem(0, R.drawable.estoque, "Estoque"),
            HomeMenuItem(1, R.drawable.pedidos, "Pedidos"),
            HomeMenuItem(2, R.drawable.vendas, "Vendas"),
            HomeMenuItem(3, R.drawable.cliente, "Clientes")
        )
        val adapter = HomePageMenuAdapter(menuCardList)

        recycleView.adapter = adapter

        adapter.onItemClick = { menuItem ->
            when (menuItem.id) {
                0 -> findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToEstoqueFragment())
                1 -> findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToPedidosFragment())
                2 -> findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToVendasFragment())
                3 -> findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToClientesFragment())
            }
        }

    }
}