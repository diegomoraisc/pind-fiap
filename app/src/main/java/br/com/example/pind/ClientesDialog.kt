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
import androidx.core.view.isVisible


class ClientesDialog : AppCompatDialogFragment() {

    companion object {
        fun newInstance(
            cliente: Cliente? = null
        ) = ClientesDialog().apply {
            this.cliente = cliente
        }
    }

    var onAddItem: ((item: Cliente) -> Unit)? = null
    private var cliente: Cliente? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.AppTheme_DialogTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.layout_popup_clientes, container, false)
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnAddCliente = view.findViewById<Button>(R.id.btn_add_cliente)

        // variável necessária para poder pegar o texto do campo de texto.
        val textNomeCliente = view.findViewById<EditText>(R.id.cliente_name_id)
        val textCnpj = view.findViewById<EditText>(R.id.cliente_cnpj_id)

        //TODO Adicionar as outras variáveis de campo de texto.

        if (cliente != null) {
            textNomeCliente.setText(cliente?.nomeCliente)
            //TODO Adicionar os outros textos quando tiver as variáveis.

            btnAddCliente.isVisible = false
        }

        btnAddCliente.setOnClickListener {
          //onAddItem?.invoke(ClientesListItem(Random.nextInt(), textNomeCliente.text.toString(), textCnpj.text.toString()))
            dismiss()
        }
    }
}