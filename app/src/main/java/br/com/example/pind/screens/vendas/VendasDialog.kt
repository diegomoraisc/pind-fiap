package br.com.example.pind.screens.vendas

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.core.view.isVisible
import br.com.example.pind.R
import br.com.example.pind.modal.cliente.PedidoCliente
import br.com.example.pind.modal.pedido.Pedido
import java.time.LocalDateTime
import kotlin.random.Random


class VendasDialog : AppCompatDialogFragment() {

    companion object {
        fun newInstance(
            venda: Pedido? = null
        ) = VendasDialog().apply {
            this.venda = venda
        }
    }

    var onAddItem: ((item: Pedido) -> Unit)? = null
    private var venda: Pedido? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.AppTheme_DialogTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.layout_popup_vendas, container, false)
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val spnNomeCliente = view.findViewById<Spinner>(R.id.spn_nome_cliente)
        val spnNomeProduto = view.findViewById<Spinner>(R.id.spn_nome_produto)
        val textQuantidade = view.findViewById<EditText>(R.id.et_quantidade)
        val textValorTotal = view.findViewById<EditText>(R.id.et_total_valor)

        val btnFechar = view.findViewById<ImageView>(R.id.iv_fechar)
        val btnAdd = view.findViewById<Button>(R.id.btn_add)
        // lista mockada
        val nomeCliente = listOf(
            "Diego",
            "Arnaldo",
            "Zé da Manga",
            "Aristovaldo"
        )

        // lista mockada
        val nomeProduto = listOf(
            "Pão",
            "Qualquer bosta",
            "Outra merda"
        )

        if (spnNomeCliente != null) {
            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, nomeCliente)
            spnNomeCliente.adapter = adapter
        }

        if (spnNomeProduto != null) {
            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, nomeProduto)
            spnNomeProduto.adapter = adapter
        }

        btnAdd.setOnClickListener {
            if (
                spnNomeCliente.selectedItem.toString().isNotEmpty() &&
                spnNomeProduto.selectedItem.toString().isNotEmpty() &&
                textQuantidade.text.toString().isNotEmpty() &&
                textValorTotal.text.toString().isNotEmpty()
            ) {
                onAddItem?.invoke(
                    Pedido(
                        Random.nextInt(),
                        LocalDateTime.now().toString(),
                        spnNomeCliente.selectedItem.toString(),
                        spnNomeProduto.selectedItem.toString(),
                        textQuantidade.text.toString(),
                        textValorTotal.text.toString().toDouble()
                    )
                )
                dismiss()
            } else {
                Toast.makeText(requireContext(), "É necessário preencher os campos.", Toast.LENGTH_SHORT).show()
            }
        }

        btnFechar.setOnClickListener {
            dismiss()
        }
    }
}