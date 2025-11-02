package com.msk.produtosperigosos.listas

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.SimpleAdapter
import androidx.appcompat.widget.AppCompatEditText
import androidx.fragment.app.ListFragment
import com.msk.produtosperigosos.R
import com.msk.produtosperigosos.info.Infracoes

class ListaInfracoes : ListFragment(), TextWatcher {

    private lateinit var adapter: SimpleAdapter
    private lateinit var textoPesquisado: AppCompatEditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.lista_infracoes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textoPesquisado = view.findViewById(R.id.etNomePesquisado)

        val listaCompleta = ArrayList<HashMap<String, String>>()

        for (i in Infracoes.codigoInfracao.indices) {
            val hm = HashMap<String, String>()
            hm["codigo"] = Infracoes.codigoInfracao[i]
            hm["amparo"] = Infracoes.amparoInfacao[i]
            hm["descricao"] = Infracoes.descricaoInfacao[i]
            listaCompleta.add(hm)
        }

        val itemlinha = arrayOf("codigo", "amparo", "descricao")

        val linhaLista = intArrayOf(R.id.tvCodigo, R.id.tvAmparo, R.id.tvInfracao)

        adapter = SimpleAdapter(
            requireActivity(),
            listaCompleta,
            R.layout.item_infracao,
            itemlinha,
            linhaLista
        )

        listAdapter = adapter

        textoPesquisado.addTextChangedListener(this)
    }

    override fun onListItemClick(l: ListView, v: View, position: Int, id: Long) {
        // ACAO QUANDO ALGUM ITEM FOR CLICADO
    }

    override fun afterTextChanged(arg0: Editable?) {
    }

    override fun beforeTextChanged(
        s: CharSequence?, start: Int, count: Int,
        after: Int
    ) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        adapter.filter.filter(s)
    }
}
