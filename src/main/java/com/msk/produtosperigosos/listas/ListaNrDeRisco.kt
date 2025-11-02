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
import com.msk.produtosperigosos.info.Riscos

class ListaNrDeRisco : ListFragment(), TextWatcher {
    private var adapter: SimpleAdapter? = null
    private var listaCompleta: MutableList<HashMap<String, String>>? = null
    private var textoPesquisado: AppCompatEditText? = null
    private lateinit var itemlinha: Array<String>
    private var linhaLista: IntArray? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.lista_nr_risco, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textoPesquisado = view.findViewById(R.id.etNomePesquisado)
        listaCompleta = ArrayList()

        for (i in Riscos.nrRisco.indices) {
            val hm = HashMap<String, String>()
            hm["nr"] = Riscos.nrRisco[i]
            hm["nome"] = Riscos.nomeRisco[i]
            listaCompleta!!.add(hm)
        }

        itemlinha = arrayOf("nr", "nome")

        linhaLista = intArrayOf(R.id.tvNrRisco, R.id.tvNomeNrRisco)

        adapter = SimpleAdapter(
            activity,
            listaCompleta,
            R.layout.item_nr_risco,
            itemlinha,
            linhaLista
        )

        listAdapter = adapter

        textoPesquisado!!.addTextChangedListener(this)
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
        adapter?.filter?.filter(s)
    }
}
