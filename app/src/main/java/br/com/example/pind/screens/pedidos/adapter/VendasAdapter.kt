package br.com.example.pind.screens.pedidos.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.NO_POSITION
import br.com.example.pind.modal.pedido.Pedido
import br.com.example.pind.R

class VendasAdapter(private val dataList: List<Pedido>) :
    RecyclerView.Adapter<VendasAdapter.VendasViewHolder>() {

    var onCheckBoxEnabled: (() -> Unit)? = null
    var isEnabled: Boolean = false
    var clearCheckBox: Boolean = false
    val selectedList = mutableListOf<Pedido>()

    inner class VendasViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val produto: TextView = itemView.findViewById<TextView>(R.id.vendas_product_name)
        val quantidade: TextView = itemView.findViewById<TextView>(R.id.vendas_quantity_content)
        val cliente: TextView = itemView.findViewById<TextView>(R.id.vendas_client)
        val data: TextView = itemView.findViewById<TextView>(R.id.vendas_date)
        val valorTotal: TextView = itemView.findViewById<TextView>(R.id.vendas_total_value)
        val card: ConstraintLayout = itemView.findViewById<ConstraintLayout>(R.id.card_vendas)
        val checkBox: CheckBox = itemView.findViewById<CheckBox>(R.id.vendas_checkBox)


        fun bind(item: Pedido) {
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
                        if(adapterPosition != NO_POSITION) {
                            selectedList.add(item)
                        }
                    } else {
                        checkBox.isChecked = false
                        selectedList.remove(item)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): VendasViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_vendas_list_item, parent, false)
        return VendasViewHolder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: VendasViewHolder, position: Int) {
        val item = dataList[position]
        holder.produto.text = item.produto
        holder.quantidade.text = item.quantidade
        holder.cliente.text = item.cliente
        holder.data.text = item.data
        holder.valorTotal.text = item.valor.toString()
        holder.checkBox.isVisible = isEnabled
        holder.bind(item)
        if (clearCheckBox) {
            holder.checkBox.isChecked = !clearCheckBox
        }
    }
}
