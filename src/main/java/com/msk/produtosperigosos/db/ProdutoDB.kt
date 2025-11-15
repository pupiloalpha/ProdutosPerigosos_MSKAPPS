package com.msk.produtosperigosos.db

// AppDatabase.kt

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Dados::class], version = 1, exportSchema = false)
abstract class ProdutoDB : RoomDatabase() {
    abstract fun produtoDAO(): ProdutoDAO

    companion object {
        @Volatile
        private var INSTANCE: ProdutoDB? = null

        fun getDatabase(context: Context): ProdutoDB {
            // Garante que só uma instância do DB é criada
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ProdutoDB::class.java,
                    "produtos_perigosos_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}