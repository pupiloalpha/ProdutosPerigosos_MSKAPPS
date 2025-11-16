package com.msk.produtosperigosos.listas

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager // NOVO
import androidx.recyclerview.widget.RecyclerView // NOVO
import com.msk.produtosperigosos.R
import com.msk.produtosperigosos.AppProdutosPerigosos
import com.msk.produtosperigosos.db.Dados
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import android.util.Log // Importar Log

class PesquisaProduto : Fragment() {

    // Variáveis de Componentes
    private lateinit var textoPesquisado: AppCompatEditText
    private lateinit var recyclerView: RecyclerView // Substitui listaProdutos: ListView

    // Variáveis de Estado e Lógica
    private lateinit var adapter: PesquisaProdutoAdapter // Novo Adapter
    private var produtosExibidos: List<Dados> = emptyList()
    private var searchJob: Job? = null

    // Acesso ao DAO (assume-se que produtoPerigosoDao está acessível via AppPerigosos)
    private val produtoDao
        get() = (requireActivity().application as AppProdutosPerigosos).produtoPerigosoDao

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.pesquisa_produtos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textoPesquisado = view.findViewById(R.id.etNomePesquisado)
        recyclerView = view.findViewById(R.id.rvProdutosPesquisados) // NOVO ID

        // 1. CONFIGURAÇÃO DO RECYCLERVIEW
        configurarRecyclerView()

        // 2. CARREGAMENTO INICIAL
        carregarListaInicial()

        // 3. CONFIGURAÇÃO DA PESQUISA (com Debounce)
        textoPesquisado.addTextChangedListener { text ->
            searchJob?.cancel()
            val query = text.toString().trim()

            if (query.length >= 3 || query.isEmpty()) {
                searchJob = lifecycleScope.launch {
                    delay(300)
                    filtrarLista(query)
                }
            }
        }
    }

    // NOVO: Função para inicializar o RecyclerView
    private fun configurarRecyclerView() {
        // Inicializa o Adapter com uma lista vazia e a lambda de click
        adapter = PesquisaProdutoAdapter(emptyList()) { position ->
            val produtoSelecionado = produtosExibidos.getOrNull(position)

            produtoSelecionado?.let {
                // Manter a lógica original de passar a POSIÇÃO para DetalheProduto
                // (Assumindo que DetalheProduto ainda usa buscarPorPosicao)
                chamarDetalheProduto(position)
            }
        }

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
    }

    private fun carregarListaInicial() {
        lifecycleScope.launch {
            val lista = produtoDao.buscarTodos()
            atualizarLista(lista)
        }
    }

    private fun filtrarLista(texto: String) {
        lifecycleScope.launch {
            val produtos = if (texto.isEmpty()) {
                produtoDao.buscarTodos()
            } else {
                val termoBusca = "%$texto%"
                produtoDao.pesquisarProdutos(termoBusca)
            }
            atualizarLista(produtos)
        }
    }

    // NOVO: Função para atualizar o RecyclerView
    private fun atualizarLista(lista: List<Dados>) {
        Log.d("PesquisaProduto", "Atualizando lista com ${lista.size} itens.") // Log para debug
        produtosExibidos = lista // Atualiza a lista de estado
        adapter.updateList(lista) // Usa a função do novo Adapter
    }

    // NOVO: Função que lida com o Intent para DetalheProduto
    private fun chamarDetalheProduto(position: Int) {
        // 1. Acessa o objeto ProdutoPerigoso na lista atualmente exibida
        val produtoSelecionado = produtosExibidos.getOrNull(position)

        // Verifica se o produto foi encontrado antes de prosseguir
        produtoSelecionado?.let { produto ->

            // 2. Monta o Bundle com o NÚMERO ONU (chave única)
            val envelope = Bundle()
            // MUDANÇA: Envia o NÚMERO ONU (String) em vez da POSIÇÃO (Int).
            // Use uma chave clara, como "NUMERO_ONU".
            envelope.putString("NUMERO_ONU", produto.numeroONU)

            // 3. Inicia a Activity
            val activityIntent = Intent("com.msk.produtosperigosos.PRODUTO")
            activityIntent.putExtras(envelope)
            requireActivity().startActivity(activityIntent)
        }
    }
}
