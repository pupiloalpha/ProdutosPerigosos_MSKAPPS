package com.msk.produtosperigosos.db

// DadosSeeder.kt

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.msk.produtosperigosos.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object DadosSeeder {

    // Função de suspensão para rodar de forma assíncrona
    suspend fun popularBanco(contexto: Context, dao: ProdutoDAO) {

        // 🚨 CRÍTICO: Rodar o I/O (leitura de arquivo) fora da Main Thread
        withContext(Dispatchers.IO) {
            // Verifica se o banco já está populado
            if (dao.buscarTodos().isNotEmpty()) {
                return@withContext
            }

            try {
                // 1. LER O JSON (que contém o seu array original de 2894 itens)
                val inputStream = contexto.resources.openRawResource(R.raw.produtos_perigosos)
                val jsonString = inputStream.bufferedReader().use { it.readText() }

                // 2. DESERIALIZAR o JSON para a sua List<ProdutoPerigoso>
                val tipoLista = object : TypeToken<List<Dados>>() {}.type
                val listaProdutos = Gson().fromJson<List<Dados>>(jsonString, tipoLista)

                // 3. INSERIR no Banco de Dados
                dao.inserirTodos(listaProdutos)

            } catch (e: Exception) {
                // Tratar erro (e.g., Logar que o JSON está inválido ou faltando)
                e.printStackTrace()
            }
        }
    }
}