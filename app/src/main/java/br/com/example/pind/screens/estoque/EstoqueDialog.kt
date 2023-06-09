package br.com.example.pind.screens.estoque

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialogFragment
import br.com.example.pind.R
import br.com.example.pind.modal.estoque.EstoqueItem
import kotlin.random.Random


class EstoqueDialog : AppCompatDialogFragment() {

    var onAddItem: ((item: EstoqueItem) -> Unit)? = null

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

        val etProduto = view.findViewById<EditText>(R.id.et_produto)
        val etQuantidade = view.findViewById<EditText>(R.id.et_quantidade)
        val etDataFrabricacao = view.findViewById<EditText>(R.id.et_data_fabricação)
        val etDataValidade = view.findViewById<EditText>(R.id.et_data_validade)
        val etUnidadeMedida = view.findViewById<EditText>(R.id.et_unidade_medida)
        val btnAdicionarProduto = view.findViewById<Button>(R.id.btn_add_product)
        val btnFechar = view.findViewById<ImageView>(R.id.iv_fechar)

        btnFechar.setOnClickListener {
            dismiss()
        }

        btnAdicionarProduto.setOnClickListener {
            if (etProduto.text.toString().isNotEmpty() &&
                etQuantidade.text.toString().isNotEmpty() &&
                etDataFrabricacao.text.toString().isNotEmpty() &&
                etDataValidade.text.toString().isNotEmpty() &&
                etUnidadeMedida.text.toString().isNotEmpty()
            ) {
                onAddItem?.invoke(
                    EstoqueItem(
                        Random.nextInt().toString(),
                        etProduto.text.toString(),
                        etQuantidade.text.toString(),
                        etDataFrabricacao.text.toString(),
                        etDataValidade.text.toString(),
                        etUnidadeMedida.text.toString()
                    )
                )
                dismiss()
            } else {
                Toast.makeText(requireContext(), "É necessário preencher os campos.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}