package br.com.example.pind

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import br.com.example.pind.api.models.ProductModel

class EstoqueListAdapter(private val dataList: List<ProductModel>) :
    RecyclerView.Adapter<EstoqueListAdapter.EstoqueViewHolder>() {

    var onItemRemoved: ((item: ProductModel) -> Unit)?=null
    private var isEnable: Boolean = false

    inner class EstoqueViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val produto = itemView.findViewById<TextView>(R.id.product_name)
        val quantidade = itemView.findViewById<TextView>(R.id.quantity_content)
        val btnRemoveEstoque = itemView.findViewById<ImageView>(R.id.remove_estoque_btn)

        fun bind(item: ProductModel) {
            btnRemoveEstoque.setOnClickListener{
                onItemRemoved?.invoke(item)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EstoqueListAdapter.EstoqueViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_estoque_list_item, parent, false)
        return EstoqueViewHolder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: EstoqueListAdapter.EstoqueViewHolder, position: Int) {
        val item = dataList[position]
        holder.produto.text = item.nome
        holder.quantidade.text = item.quantidade.toString()
        holder.btnRemoveEstoque.isVisible = isEnable
        holder.bind(item)
    }

    fun onRemoveEnable() {
        isEnable = !isEnable
    }
}
