package br.com.example.pind.screens.clientes

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
import androidx.core.view.isVisible
import br.com.example.pind.R
import br.com.example.pind.modal.cliente.PedidoCliente
import kotlin.random.Random


class ClientesDialog : AppCompatDialogFragment() {

    companion object {
        fun newInstance(
            cliente: PedidoCliente? = null
        ) = ClientesDialog().apply {
            this.cliente = cliente
        }
    }

    var onAddItem: ((item: PedidoCliente) -> Unit)? = null
    private var cliente: PedidoCliente? = null

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

        val btnFechar = view.findViewById<ImageView>(R.id.iv_fechar)
        val btnAddCliente = view.findViewById<Button>(R.id.btn_add_cliente)

        // variável necessária para poder pegar o texto do campo de texto.
        val textNomeCliente = view.findViewById<EditText>(R.id.cliente_name_id)
        val textCnpj = view.findViewById<EditText>(R.id.cliente_cnpj_id)
        val textEmail = view.findViewById<EditText>(R.id.cliente_email_id)
        val textTelefone = view.findViewById<EditText>(R.id.cliente_telefone_id)
        val textCep = view.findViewById<EditText>(R.id.cliente_cep_id)
        val textRua = view.findViewById<EditText>(R.id.cliente_rua_id)
        val textBairro = view.findViewById<EditText>(R.id.cliente_bairro_id)
        val textCidade = view.findViewById<EditText>(R.id.cliente_cidade_id)
        val textEstado = view.findViewById<EditText>(R.id.cliente_estado_id)

        if (cliente != null) {
            textNomeCliente.setText(cliente?.nomeCliente)
            textCnpj.setText(cliente?.cnpj)
            textEmail.setText(cliente?.email)
            textTelefone.setText(cliente?.telefone)
            textCep.setText(cliente?.cep)
            textRua.setText(cliente?.rua)
            textBairro.setText(cliente?.bairro)
            textCidade.setText(cliente?.cidade)
            textEstado.setText(cliente?.estado)

            btnAddCliente.isVisible = false
        }

        btnAddCliente.setOnClickListener {
            if (textNomeCliente.text.toString().isNotEmpty() &&
                textCnpj.text.toString().isNotEmpty() &&
                textEmail.text.toString().isNotEmpty() &&
                textTelefone.text.toString().isNotEmpty() &&
                textCep.text.toString().isNotEmpty() &&
                textRua.text.toString().isNotEmpty() &&
                textBairro.text.toString().isNotEmpty() &&
                textCidade.text.toString().isNotEmpty() &&
                textEstado.text.toString().isNotEmpty()
            ) {
                onAddItem?.invoke(
                    PedidoCliente(
                        Random.nextInt(),
                        textNomeCliente.text.toString(),
                        textCnpj.text.toString(),
                        textEmail.text.toString(),
                        textTelefone.text.toString(),
                        textCep.text.toString(),
                        textRua.text.toString(),
                        textBairro.text.toString(),
                        textCidade.text.toString(),
                        textEstado.text.toString(),
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