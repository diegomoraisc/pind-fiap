package br.com.example.pind

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatDialogFragment


class EstoqueDialog : AppCompatDialogFragment() {

    var onAddItem: ((item: EstoqueListItem) -> Unit)? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.AppTheme_DialogTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.layout_popup_estoque, container, false)
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnAddProduct = view.findViewById<Button>(R.id.btn_add_product)
        val textProduct = view.findViewById<EditText>(R.id.product_id)
        val textQuantity = view.findViewById<EditText>(R.id.quantity_id)

        btnAddProduct.setOnClickListener {
            onAddItem?.invoke(EstoqueListItem(textProduct.text.toString(), textQuantity.text.toString()))
            dismiss()
        }
    }
}