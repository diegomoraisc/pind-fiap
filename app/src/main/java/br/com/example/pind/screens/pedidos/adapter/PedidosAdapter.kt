package br.com.example.pind.screens.pedidos.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.example.pind.R
import br.com.example.pind.modal.pedido.PedidosCliente

class PedidosAdapter(private val dataList: List<PedidosCliente>) :
    RecyclerView.Adapter<PedidosAdapter.PedidosViewHolder>() {

    inner class PedidosViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textCliente: TextView = itemView.findViewById<TextView>(R.id.client_name)
        val textPedidos: TextView = itemView.findViewById<TextView>(R.id.quantity_requests)
        val textPedidosFinalizados: TextView = itemView.findViewById<TextView>(R.id.finished_content)
        val textPedidosAndamentos: TextView = itemView.findViewById<TextView>(R.id.quantity_in_progress)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PedidosViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_pedidos_list_item, parent, false)
        return PedidosViewHolder(view)
    }

    override fun getItemCount() = dataList.size

    override fun onBindViewHolder(holder: PedidosViewHolder, position: Int) {
        val item = dataList[position]

        holder.textCliente.text = item.cliente
        holder.textPedidos.text = item.pedidos.toString()
        holder.textPedidosFinalizados.text = item.pedidosFinalizados.toString()
        holder.textPedidosAndamentos.text = item.pedidosAndamentos.toString()
    }
}