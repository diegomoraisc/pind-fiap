package br.com.example.pind.screens.estoque

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatDialogFragment
import br.com.example.pind.R
import br.com.example.pind.api.caller.ProductsCaller
import br.com.example.pind.api.models.ProductModel
import br.com.example.pind.modal.cliente.services.ProductsService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EstoqueDialog : AppCompatDialogFragment() {

    var onAddItem: (() -> Unit)? = null

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
        val textProduct = view.findViewById<EditText>(R.id.et_produto)
        val textQuantity = view.findViewById<EditText>(R.id.et_quantidade)
        val textPrice = view.findViewById<EditText>(R.id.price)
        val textMeasurement = view.findViewById<EditText>(R.id.unit_measurement)

        btnAddProduct.setOnClickListener {
            val call = ProductsCaller(requireContext()).productsService().create(ProductsService :: class.java).postProduct(ProductModel(
                textProduct.text.toString(),
                textPrice.text.toString().toInt(),
                textQuantity.text.toString().toInt(),
                textMeasurement.text.toString()
            ))
            call.enqueue(object : Callback<ProductModel> {
                override fun onResponse(
                    call: Call<ProductModel>,
                    response: Response<ProductModel>
                ) {
                    response.body()?.let {
                        Log.i("Product", it.toString())
                        onAddItem?.invoke()
                    }
                }
                override fun onFailure(call: Call<ProductModel>, t: Throwable) {
                    t?.message?.let { it1 -> Log.e("Erro", it1) }

                }

            }
            )
            dismiss()
        }

    }
}