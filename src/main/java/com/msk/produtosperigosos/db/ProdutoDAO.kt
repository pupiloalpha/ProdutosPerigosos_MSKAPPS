package com.msk.produtosperigosos.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ProdutoDAO {

    // 💡 Função de População Única: Para inserir todos os 2894 registros.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserirTodos(produtos: List<Dados>)

    // 🔎 Função de Busca Otimizada: Para a tela DetalheProduto.kt
    @Query("SELECT * FROM produtos_perigosos WHERE numeroONU = :onu LIMIT 1")
    suspend fun buscarPorONU(onu: String?): Dados?

    // 📜 Função de Consulta (Para a lista inicial de busca, por exemplo)
    @Query("SELECT * FROM produtos_perigosos ORDER BY numeroONU ASC")
    suspend fun buscarTodos(): List<Dados>

    // Busca um produto pela sua posição na lista.
    @Query("SELECT * FROM produtos_perigosos ORDER BY numeroONU ASC LIMIT 1 OFFSET :posicao")
    suspend fun buscarPorPosicao(posicao: Int): Dados?

    // Consulta para a pesquisa na tela. Busca por ONU ou Descrição
    @Query("SELECT * FROM produtos_perigosos WHERE numeroONU LIKE :query OR descricao LIKE :query ORDER BY numeroONU ASC")
    suspend fun pesquisarProdutos(query: String): List<Dados>

}