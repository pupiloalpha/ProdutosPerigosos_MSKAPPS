package com.msk.produtosperigosos

import android.app.Application
import com.msk.produtosperigosos.db.DadosSeeder
import com.msk.produtosperigosos.db.ProdutoDAO // Importar o DAO (ProdutoPerigosoDao)
import com.msk.produtosperigosos.db.ProdutoDB // Importar a classe AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class AppProdutosPerigosos : Application() {

    /**
     * CoroutineScope para tarefas assíncronas de longa duração que
     * devem sobreviver às Activities (como a população do DB).
     */
    val applicationScope = CoroutineScope(SupervisorJob())

    /**
     * Instância Lazy (preguiçosa) do Banco de Dados.
     * O DB só é criado na primeira vez que você acessa esta propriedade 'database'.
     */
    val database by lazy {
        ProdutoDB.getDatabase(this)
    }

    // Você também pode adicionar o DAO aqui para facilitar o acesso:
    val produtoPerigosoDao: ProdutoDAO by lazy {
        database.produtoDAO()
    }

    override fun onCreate() {
        super.onCreate()

        // Chamada de inicialização de dados (Seeding)
        // Isso deve ser feito fora da Main Thread, usando o CoroutineScope
        applicationScope.launch {
            DadosSeeder.popularBanco(this@AppProdutosPerigosos, database.produtoDAO())
        }
    }
}