package br.com.example.pind.screens.clientes.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import br.com.example.pind.R
import br.com.example.pind.modal.cliente.PedidoCliente

class ClientesListAdapter(private val dataList: List<PedidoCliente>) :
    RecyclerView.Adapter<ClientesListAdapter.ClientesViewHolder>() {


    var onItemClicked: ((cliente: PedidoCliente) -> Unit)? = null
    var onClickFinalizados: ((cliente: PedidoCliente) -> Unit)? = null
    var onClickEmAndamento: ((cliente: PedidoCliente) -> Unit)? = null
    var onCheckBoxEnabled: (() -> Unit)? = null
    var isEnabled: Boolean = false
    var clearCheckBox: Boolean = false
    val selectedList = mutableListOf<PedidoCliente>()

    inner class ClientesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nomeCliente: TextView = itemView.findViewById(R.id.client_name)
        val primeiraCompra: TextView = itemView.findViewById(R.id.primeira_compra_data)
        val ultimaCompra: TextView = itemView.findViewById(R.id.ultima_compra_data)
        val btnFinalizados: Button = itemView.findViewById(R.id.btn_finalizados)
        val btnEmAndamento: Button = itemView.findViewById(R.id.btn_em_andamento)
        val card: ConstraintLayout = itemView.findViewById(R.id.card_clientes)
        val checkBox: CheckBox = itemView.findViewById(R.id.clientes_checkBox)


        fun bind(item: PedidoCliente) {
            card.setOnLongClickListener {
                isEnabled = true
                checkBox.isVisible = isEnabled
                onCheckBoxEnabled?.invoke()
                true
            }
            card.setOnClickListener {
                if (isEnabled) {
                    if (!checkBox.isChecked) {
                        checkBox.isChecked = true
                        selectedList.add(item)
                    } else {
                        checkBox.isChecked = false
                        selectedList.remove(item)
                    }
                }
            }
            itemView.setOnClickListener {
                onItemClicked?.invoke(item)
            }
            btnFinalizados.setOnClickListener {
                onClickFinalizados?.invoke(item)
            }
            btnEmAndamento.setOnClickListener {
                onClickEmAndamento?.invoke(item)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ClientesViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_clientes_list_item, parent, false)
        return ClientesViewHolder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: ClientesViewHolder, position: Int) {
        val item = dataList[position]
        holder.nomeCliente.text = item.nomeCliente
        holder.primeiraCompra.text = item.dataPrimeiraCompra
        holder.ultimaCompra.text = item.dataUltimaCompra
        holder.checkBox.isVisible = isEnabled
        holder.bind(item)
        if (clearCheckBox) {
            holder.checkBox.isChecked = !clearCheckBox
        }
    }
}
