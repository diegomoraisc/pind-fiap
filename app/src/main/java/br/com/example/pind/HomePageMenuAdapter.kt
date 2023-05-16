package br.com.example.pind

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class HomePageMenuAdapter(private val dataList: List<HomeMenuItem>) :
    RecyclerView.Adapter<HomePageMenuAdapter.HomePageViewHolder>() {

    var onItemClick: ((item: HomeMenuItem) -> Unit)? = null

    inner class HomePageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image = itemView.findViewById<ImageView>(R.id.image_card)
        val title = itemView.findViewById<TextView>(R.id.title_card)
        val card = itemView.findViewById<CardView>(R.id.card_view)

        fun bind(item: HomeMenuItem) {
            card.setOnClickListener {
                onItemClick?.invoke(item)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomePageMenuAdapter.HomePageViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_home_list_item, parent, false)
        return HomePageViewHolder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: HomePageMenuAdapter.HomePageViewHolder, position: Int) {
        val item = dataList[position]
        holder.title.text = item.title
        holder.image.setImageResource(item.image)
        holder.bind(item)

    }
}