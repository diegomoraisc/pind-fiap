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
import kotlin.random.Random


class ClientesDialog : AppCompatDialogFragment() {

    var onAddItem: ((item: ClientesListItem) -> Unit)? = null

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
        val textNomeCliente = view.findViewById<EditText>(R.id.cliente_name_id)
        val textCnpj = view.findViewById<EditText>(R.id.cliente_cnpj_id)

        //TODO Adicionar as outras variáveis

        btnAddCliente.setOnClickListener {
          //onAddItem?.invoke(ClientesListItem(Random.nextInt(), textNomeCliente.text.toString(), textCnpj.text.toString()))
            dismiss()
        }
    }
}