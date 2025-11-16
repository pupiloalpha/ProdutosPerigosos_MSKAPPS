package com.msk.produtosperigosos.listas

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.msk.produtosperigosos.R
import com.msk.produtosperigosos.db.Dados // Sua Entidade

class PesquisaProdutoAdapter (
    private var produtos: List<Dados>,
    private val onItemClick: (Int) -> Unit // Lambda para lidar com o click
) : RecyclerView.Adapter<PesquisaProdutoAdapter.ProdutoViewHolder>(){
    // 1. ViewHolder: Armazena as referências dos componentes do item
    inner class ProdutoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNrONU: TextView = itemView.findViewById(R.id.tvNrONU)
        val tvNomeProduto: TextView = itemView.findViewById(R.id.tvNomeProduto)

        init {
            // Lógica de click que passa a POSIÇÃO do item (índice na lista exibida)
            itemView.setOnClickListener {
                if (bindingAdapterPosition != RecyclerView.NO_POSITION) {
                    onItemClick(bindingAdapterPosition)
                }
            }
        }

        fun bind(produto: Dados) {
            tvNrONU.text = produto.numeroONU
            tvNomeProduto.text = produto.descricao
        }
    }

    // 2. Cria as Views (chamado poucas vezes, apenas para preencher a tela)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProdutoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_produto, parent, false)
        return ProdutoViewHolder(view)
    }

    // 3. Faz o bind de dados (chamado muitas vezes, reutilizando as Views)
    override fun onBindViewHolder(holder: ProdutoViewHolder, position: Int) {
        holder.bind(produtos[position])
    }

    // 4. Retorna o tamanho da lista (necessário para o RecyclerView)
    override fun getItemCount() = produtos.size

    // 5. Função para atualizar a lista de dados (CRÍTICA para a busca)
    fun updateList(newList: List<Dados>) {
        produtos = newList
        notifyDataSetChanged() // Notifica o RecyclerView sobre a mudança (pode ser melhorado com DiffUtil)
    }

}