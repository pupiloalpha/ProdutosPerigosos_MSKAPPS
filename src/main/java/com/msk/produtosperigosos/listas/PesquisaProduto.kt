package com.msk.produtosperigosos.listas

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.SimpleAdapter
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.msk.produtosperigosos.R

class PesquisaProduto : Fragment() {

    private val listaCompleta = mutableListOf<HashMap<String, String>>()
    private lateinit var textoPesquisado: AppCompatEditText
    private lateinit var listaProdutos: ListView
    private var adapter: SimpleAdapter? = null
    private lateinit var nrONU: Array<String>
    private lateinit var nomeProduto: Array<String>
    private lateinit var dadosProduto: Array<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.pesquisa_produtos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textoPesquisado = view.findViewById(R.id.etNomePesquisado)
        listaProdutos = view.findViewById(R.id.lvProdutosPesquisados)

        val resources = requireContext().resources
        nrONU = resources.getStringArray(R.array.nr_onu)
        nomeProduto = resources.getStringArray(R.array.nome_produto)
        dadosProduto = resources.getStringArray(R.array.dados_produto)

        for (i in nrONU.indices) {
            val hm = hashMapOf(
                "nr" to nrONU[i],
                "nome" to nomeProduto[i],
                "dados" to dadosProduto[i],
                "index" to i.toString()
            )
            listaCompleta.add(hm)
        }

        val from = arrayOf("nr", "nome")
        val to = intArrayOf(R.id.tvNrONU, R.id.tvNomeProduto)

        adapter = SimpleAdapter(
            requireActivity(),
            listaCompleta,
            R.layout.item_produto,
            from,
            to
        )

        listaProdutos.adapter = adapter
        listaProdutos.setOnItemClickListener { _, _, position, _ ->
            @Suppress("UNCHECKED_CAST")
            val item = adapter?.getItem(position) as? HashMap<String, String>
            item?.let {
                val originalIndex = it["index"]?.toIntOrNull()
                if (originalIndex != null) {
                    val envelope = Bundle()
                    envelope.putInt("nr", originalIndex)
                    val activityIntent = Intent("com.msk.produtosperigosos.PRODUTO")
                    activityIntent.putExtras(envelope)
                    requireActivity().startActivity(activityIntent)
                }
            }
        }

        textoPesquisado.addTextChangedListener { text ->
            adapter?.filter?.filter(text)
        }
    }
}
