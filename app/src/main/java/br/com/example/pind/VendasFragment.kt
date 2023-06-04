package br.com.example.pind

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView

class VendasFragment:Fragment() {
    var btnCancel: Button? = null
    var btnAdd: Button? = null
    var btnRemove: Button? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_vendas, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_vendas)
        btnRemove = view.findViewById<Button>(R.id.btn_remove_vendas)
        btnAdd = view.findViewById<Button>(R.id.btn_add_vendas)
        btnCancel = view.findViewById<Button>(R.id.btn_cancel)
        val vendasList = mutableListOf(
            VendasListItem(0,"14 maio 2023", "Cliente X", "Pão", "5000", 7000.00),
            VendasListItem(1,"14 maio 2023", "Cliente Y", "Alho", "6000", 8000.00),
            VendasListItem(2,"14 maio 2023", "Cliente Zé", "Manga", "7000", 9000.00),
            VendasListItem(3,"14 maio 2023", "Cliente A", "Cebola", "8000", 10000.00),
        )
        val vendasAdapter = VendasListAdapter(vendasList)
        recyclerView.adapter = vendasAdapter
        vendasAdapter.onCheckBoxEnabled = {
            vendasAdapter.notifyItemRangeChanged(0, vendasList.size)
            setButtonVisibility(true)
        }
        btnCancel?.setOnClickListener {
            vendasAdapter.isEnabled = false
            vendasAdapter.clearCheckBox = true
            vendasAdapter.selectedList.clear()
            vendasAdapter.notifyItemRangeChanged(0, vendasList.size)
            setButtonVisibility(false)
        }
        btnRemove?.setOnClickListener {
            vendasAdapter.isEnabled = false
            vendasAdapter.clearCheckBox = true
            vendasAdapter.selectedList.forEach{ item ->
                val position = vendasList.indexOf(item)
                vendasList.removeAt(position)
                vendasAdapter.notifyItemRemoved(position)
            }
        }
    }

    private fun setButtonVisibility (isVisible: Boolean){
        btnRemove?.isVisible = isVisible
        btnCancel?.isVisible = isVisible
        btnAdd?.isVisible = !isVisible
    }
}