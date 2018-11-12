package br.com.heiderlopes.helloandroid.ui.main

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.CompoundButton
import android.widget.Toast
import br.com.heiderlopes.helloandroid.R
import br.com.heiderlopes.helloandroid.model.Pedido
import br.com.heiderlopes.helloandroid.ui.checkout.CheckoutActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), CompoundButton.OnCheckedChangeListener {

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        when (buttonView?.id) {
            R.id.cbAtum -> mainViewModel.atumSelecionado = isChecked
            R.id.cbBacon -> mainViewModel.baconSelecionado = isChecked
            R.id.cbCalabresa -> mainViewModel.calabresaSelecionada = isChecked
            R.id.cbMussarela -> mainViewModel.mussarelaSelecioda = isChecked
        }
    }

    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProviders
                .of(this)
                .get(MainViewModel::class.java)

        mainViewModel.nomeCliente = intent.getStringExtra("nome")
        mainViewModel.telefoneCliente = intent.getStringExtra("telefone")

        tvNome.text = getString(R.string.saudacao,
                mainViewModel.nomeCliente,
                mainViewModel.telefoneCliente)

        cbAtum.isChecked = mainViewModel.atumSelecionado
        cbBacon.isChecked = mainViewModel.baconSelecionado
        cbCalabresa.isChecked = mainViewModel.calabresaSelecionada
        cbMussarela.isChecked = mainViewModel.mussarelaSelecioda

        btCalcular.setOnClickListener {
            val intent = Intent(this,
                    CheckoutActivity::class.java)
            intent.putExtra("pedido", gerarPedido())
            startActivity(intent)
        }
    }

    private fun gerarPedido(): Pedido {
        return Pedido(mainViewModel.nomeCliente,
                mainViewModel.telefoneCliente)
    }
}
